package org.eduardomaravill.sdd_catalogo.services.auth.implement;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.eduardomaravill.sdd_catalogo.dtos.auth.*;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.NewUpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UserSaveDto;
import org.eduardomaravill.sdd_catalogo.exceptions.ObjectNotFoundException;
import org.eduardomaravill.sdd_catalogo.exceptions.UsernameOrEmailDuplicateException;
import org.eduardomaravill.sdd_catalogo.models.users_models.JwtToken;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;
import org.eduardomaravill.sdd_catalogo.services.auth.IAuthenticationService;
import org.eduardomaravill.sdd_catalogo.services.auth.IJwtService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IEmailService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IReCaptchaService;
import org.eduardomaravill.sdd_catalogo.services.users_services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.eduardomaravill.sdd_catalogo.utils.GeoLocation.validateLocation;
import static org.eduardomaravill.sdd_catalogo.utils.MyEncoderBase64.decodeBase64ToDouble;
import static org.eduardomaravill.sdd_catalogo.utils.MyEncoderBase64.encoderDoubleToBase64;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7}$";

    private static final String ERROR_MESSAGE = "Username or email don´t register!";

    private final IUserService userService;

    private final IJwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final IEmailService emailService;

    private final IReCaptchaService reCaptchaService;


    @Autowired
    public AuthenticationServiceImpl(IUserService userService, IJwtService jwtService, AuthenticationManager authenticationManager, IEmailService emailService, IReCaptchaService reCaptchaService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.reCaptchaService = reCaptchaService;
    }

    @Override
    public ValidTokenResponse registerOneRacer(UserSaveDto newUser) {
        User registeredUser = userService.registerOneRacer(newUser);
        String jwtToken = jwtService.generateTokenEmailValidate(registeredUser, generateExtraClaimsForEmail(registeredUser.getEmail(),
                newUser.getLatitude(), newUser.getLongitude()));
        saveUserAndToken(registeredUser, jwtToken);
        emailService.sendEmailVerification(registeredUser,jwtToken);
        return new ValidTokenResponse(true);
    }

    private void saveUserAndToken(User registeredUser, String jwtToken) {
        JwtToken token = new JwtToken();
        token.setToken(jwtToken);
        token.setUser(registeredUser);
        token.setExpiration(jwtService.extractExpiration(jwtToken));
        token.setIsValid(true);
        jwtService.jwtSaveToken(token);
    }

    private Map<String, Object> generateExtraClaims(User registeredUser,Double latitude, Double longitude) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", registeredUser.getName());
        extraClaims.put("username", registeredUser.getUsername());
        extraClaims.put("email", registeredUser.getEmail());
        extraClaims.put("role", registeredUser.getRole().getName());
        extraClaims.put("registerTime", System.currentTimeMillis());
        extraClaims.put("latitude", encoderDoubleToBase64(latitude));
        extraClaims.put("longitude", encoderDoubleToBase64(longitude));
        return extraClaims;
    }

    private Map<String, Object> generateExtraClaimsForEmail(String email,Double latitude, Double longitude) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", email);
        extraClaims.put("registerTime", System.currentTimeMillis());
        extraClaims.put("latitude", encoderDoubleToBase64(latitude));
        extraClaims.put("longitude", encoderDoubleToBase64(longitude));
        return extraClaims;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest autRequest) {
        String usernameOrEmail = autRequest.getUsernameOrEmail();
        User user;
        if (isValidEmail(usernameOrEmail)) {
            user = userService.findOneByEmail(usernameOrEmail).orElseThrow(
                    () -> new ObjectNotFoundException(ERROR_MESSAGE)
            );
        } else {
            user = userService.findOneByUsername(usernameOrEmail).orElseThrow(
                    () -> new ObjectNotFoundException(ERROR_MESSAGE)
            );
        }
        //verify credentials
        authenticateCurrentUser(user.getUsername(), autRequest.getPassword());

        if (!user.isEmailValid()){
            String tokenEmail = jwtService.generateTokenEmailValidate(user, generateExtraClaimsForEmail(user.getEmail(),autRequest.getLatitude(),autRequest.getLongitude()));
            saveUserAndToken(user, tokenEmail);
            emailService.sendEmailVerification(user,tokenEmail);
            throw new AccessDeniedException("Email not validated!");
        }
        String jwtToken = jwtService.generateToken(user, generateExtraClaims(user,autRequest.getLatitude(),autRequest.getLongitude()));
        saveUserAndToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken);
    }

    private void authenticateCurrentUser(String username, String password){
        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(authentication);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }


    @Transactional
    @Override
    public Boolean validateToken(HttpServletRequest request) {
        String jwtToken = jwtService.extractJwtFromRequest(request);
        Optional<JwtToken> jwtTokenVerify = jwtService.findJwtToken(jwtToken);
        boolean isValid = jwtTokenVerify.isPresent() &&
                Boolean.TRUE.equals(jwtTokenVerify.get().getIsValid()) &&
                jwtTokenVerify.get().getExpiration().isAfter(LocalDateTime.now()) &&
                validateLocation(jwtService.extractLocationFromToken(jwtToken),jwtService.extractLocationFromRequest(request));
        if (!isValid){
          jwtService.invalidTokenJwt(jwtToken);
        }
        return isValid;
    }

    @Override
    public UserProfileResponse findLoggedInUser() {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        User user = userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException(ERROR_MESSAGE));
        UserProfileResponse userProfileResponse = new UserProfileResponse();
        userProfileResponse.setName(user.getName());
        userProfileResponse.setUsername(user.getUsername());
        userProfileResponse.setEmail(user.getEmail());
        userProfileResponse.setRole(user.getRole().getName());
        userProfileResponse.setColor(user.getColorProfile());
        return userProfileResponse;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String jwtToken = jwtService.extractJwtFromRequest(request);
        if (!StringUtils.hasText(jwtToken)) return;
        jwtService.invalidTokenJwt(jwtToken);
    }


    @Override
    public NewUserProfileResponse updateProfileOneRacer(HttpServletRequest request, UserEditProfileRequest userEditProfileRequest) {
        Double latitude= decodeBase64ToDouble(request.getHeader("Latitude")) ;
        Double longitude= decodeBase64ToDouble(request.getHeader("Longitude")) ;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(userEditProfileRequest.getEmailEdit(),
                userEditProfileRequest.getPasswordEdit(),latitude,longitude);
        AuthenticationResponse authenticationResponse = login(authenticationRequest);
        jwtService.invalidTokenJwt(authenticationResponse.getTokenJwt());

        User user = userService.findOneByEmail(userEditProfileRequest.getEmailEdit())
                .orElseThrow(() -> new ObjectNotFoundException(ERROR_MESSAGE));

        if (!user.getUsername().equals(userEditProfileRequest.getUsernameEdit()) && userService.findOneByUsername(userEditProfileRequest.getUsernameEdit()).isPresent()) {
            throw new UsernameOrEmailDuplicateException("User already exists");
        }
        logout(request);
        if (!user.getRole().getName().equals(userEditProfileRequest.getRoleEdit()) ||
                !user.getEmail().equals(userEditProfileRequest.getEmailEdit())) {
            throw new AccessDeniedException("You don't have permission to modify email or role");
        }
        user.setName(userEditProfileRequest.getNameEdit());
        user.setUsername(userEditProfileRequest.getUsernameEdit());
        user.setColorProfile(userEditProfileRequest.getColorEdit());

        Optional<User> userUpdated = userService.updateUser(user);
        if (userUpdated.isPresent()) {
            authenticationRequest.setUsernameOrEmail(user.getUsername());
            authenticationRequest.setPassword(userEditProfileRequest.getPasswordEdit());
            authenticationRequest.setLatitude(latitude);
            authenticationRequest.setLongitude(longitude);
            authenticationResponse = login(authenticationRequest);

            User newUser = userUpdated.get();
            UserProfileResponse userProfileResponse = new UserProfileResponse(
                    newUser.getName(), newUser.getUsername(), newUser.getEmail(), newUser.getRole().getName(), newUser.getColorProfile()
            );
            return new NewUserProfileResponse(userProfileResponse, authenticationResponse);
        }

        return null;
    }

    @Override
    public NewUserProfileResponse updateEmailVerified(HttpServletRequest request) {
        String jwtToken = jwtService.extractJwtFromRequest(request);
        String username = jwtService.extractUsername(jwtToken);
        Map<String,String> location= jwtService.extractLocationFromToken(jwtToken);
        jwtService.invalidTokenJwt(jwtToken);
        User user = userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException(ERROR_MESSAGE));
        user.setEmailValid(true);
        Optional<User> userUpdated = userService.updateUser(user);
        if (userUpdated.isPresent()) {
            UserProfileResponse userProfileResponse = new UserProfileResponse(
                    userUpdated.get().getName(), userUpdated.get().getUsername(), userUpdated.get().getEmail(), userUpdated.get().getRole().getName(), userUpdated.get().getColorProfile()
            );
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String newToken = jwtService.generateToken(userUpdated.get(),generateExtraClaims(userUpdated.get(),
                    decodeBase64ToDouble(location.get("latitude")),decodeBase64ToDouble(location.get("longitude"))));
            authenticationResponse.setTokenJwt(newToken);
            saveUserAndToken(userUpdated.get(),newToken);
            emailService.sendEmailWelcome(userUpdated.get());
            return new NewUserProfileResponse(userProfileResponse, authenticationResponse);
        }
        return null;
    }

    @Override
    public ValidTokenResponse resetPassword(HttpServletRequest request) {
        String email = request.getHeader("resetPasswordEmail");
        String latitude = request.getHeader("Latitude");
        String longitude = request.getHeader("Longitude");
        if (isValidEmail(email)){
            User user = userService.findOneByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email not found!"));
            String jwtToken = jwtService.generateTokenEmailValidate(user, generateExtraClaimsForEmail(user.getEmail(),
                    decodeBase64ToDouble(latitude),decodeBase64ToDouble(longitude)));
            saveUserAndToken(user, jwtToken);
            emailService.sendEmailPasswordReset(user,jwtToken);
            return new ValidTokenResponse(true);
        }
        return new ValidTokenResponse(false);
    }

    @Override
    public ValidTokenResponse updatePassword(HttpServletRequest request, UpdatePasswordRequest updatePasswordRequest) {
        String jwtToken = jwtService.extractJwtFromRequest(request);
        String username = jwtService.extractUsername(jwtToken);
        jwtService.invalidTokenJwt(jwtToken);
        User user = userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        Optional<User> userUpdated = userService.updatePasswordUser(user,updatePasswordRequest);
        if (userUpdated.isPresent()){
            emailService.sendEmailResetPasswordSuccess(userUpdated.get());
            return new ValidTokenResponse(true);
        }
        return new ValidTokenResponse(false);
    }

    @Override
    public ValidTokenResponse verifyReCaptchaToken(String token) {
        return reCaptchaService.verifyReCaptcha(token);
    }

    @Override
    public NewUserProfileResponse profilePasswordUpdate(HttpServletRequest request, NewUpdatePasswordRequest newUpdatePasswordRequest) {
        String jwtToken = jwtService.extractJwtFromRequest(request);
        String username = jwtService.extractUsername(jwtToken);
        User user = userService.findOneByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found!"));
        authenticateCurrentUser(user.getUsername(), newUpdatePasswordRequest.getCurrentPassword());
        Optional<User> userUpdated = userService.updatePasswordUser(user, new UpdatePasswordRequest(newUpdatePasswordRequest.getNewPassword(), newUpdatePasswordRequest.getConfirmNewPassword()));
        if (userUpdated.isPresent()){
            jwtService.invalidTokenJwt(jwtToken);
            AuthenticationResponse authResponse = login(new AuthenticationRequest(userUpdated.get().getUsername(),
                    newUpdatePasswordRequest.getNewPassword(),
                    decodeBase64ToDouble(request.getHeader("Latitude")),
                    decodeBase64ToDouble(request.getHeader("Longitude"))));
            User newUser = userUpdated.get();
            UserProfileResponse userProfileResponse = new UserProfileResponse(
                    newUser.getName(), newUser.getUsername(), newUser.getEmail(), newUser.getRole().getName(), newUser.getColorProfile()
            );
            return new NewUserProfileResponse(userProfileResponse, authResponse);
        }
        return null;
    }

    @Override
    public ValidTokenResponse sendContactEmail(ContactFormRequest contactFormRequest) {
        emailService.sendContactEmail(contactFormRequest);
        return new ValidTokenResponse(true);
    }
}
