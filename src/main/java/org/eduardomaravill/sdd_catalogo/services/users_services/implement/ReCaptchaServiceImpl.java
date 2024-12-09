package org.eduardomaravill.sdd_catalogo.services.users_services.implement;

import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;
import org.eduardomaravill.sdd_catalogo.services.users_services.IReCaptchaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ReCaptchaServiceImpl implements IReCaptchaService {

    @Value("${recaptcha.secret-key}")
    private String reCaptchaApiSecretKey;

    @Value("${recaptcha.verify.url}")
    private String reCaptchaVerifyUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ValidTokenResponse verifyReCaptcha(String token) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("response", token);
        formData.add("secret", reCaptchaApiSecretKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                reCaptchaVerifyUrl,
                HttpMethod.POST, request,
                new ParameterizedTypeReference<>() {
                });

        Map<String, Object> body = responseEntity.getBody();

        if (body != null) {
            Boolean success = (Boolean) body.get("success");
            return new ValidTokenResponse(success);
        }
        return new ValidTokenResponse(false);
    }
}