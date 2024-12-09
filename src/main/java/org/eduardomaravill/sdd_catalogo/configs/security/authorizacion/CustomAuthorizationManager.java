package org.eduardomaravill.sdd_catalogo.configs.security.authorizacion;

import jakarta.servlet.http.HttpServletRequest;
import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.models.users_models.GrantedPermission;
import org.eduardomaravill.sdd_catalogo.models.users_models.Operation;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.eduardomaravill.sdd_catalogo.repositories.users_respositories.IOperationRepository;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {


    private final IOperationRepository operationRepository;
    
    private final IUserService userService;

    @Autowired
    public CustomAuthorizationManager(IOperationRepository operationRepository, IUserService userService) {
        this.operationRepository = operationRepository;
        this.userService = userService;
    }

    @Override
    public AuthorizationDecision check(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String url = extractURl(request);
        String httpMethod = request.getMethod();
        boolean isPublic = isPublic(url, httpMethod);
        if (isPublic) {
            return new AuthorizationDecision(true);
        }
        Boolean isGranted = isGranted(url, httpMethod, authentication.get());
        return new AuthorizationDecision(isGranted);
    }

    private Boolean isGranted(String url, String httpMethod, Authentication authentication) {
        if (!(authentication instanceof UsernamePasswordAuthenticationToken)) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }
        List<Operation> operations = obtainedOperations(authentication);
        return operations.stream().anyMatch(
                getOperationPredicate(url, httpMethod)
        );
    }

    private List<Operation> obtainedOperations(Authentication authentication) {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        User user = userService.findOneByUsername(username).orElseThrow(
                () -> new ObjectNotFoundException(username + " not found")
        );

        return user.getRole().getPermissions().stream().map(GrantedPermission::getOperation)
                .toList();
    }

    private Boolean isPublic(String url, String httpMethod) {
        List<Operation> publicAccessEndPoints = operationRepository.findByPublicAccess();
        return publicAccessEndPoints.stream().anyMatch(getOperationPredicate(url, httpMethod));
    }

    private Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {
            String basePath = operation.getModule().getBasePath();
            String path = operation.getPath();
            if (basePath == null) {
                basePath = "";
            }
            if (path == null) {
                path = "";
            }
            String  urlPath = basePath.concat(path);
            Pattern pattern = Pattern.compile(urlPath);
            Matcher matcher = pattern.matcher(url);
            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private String extractURl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        return url;
    }
}
