package org.eduardomaravill.sdd_catalogo.services.users_services;

import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;

public interface IReCaptchaService {
    ValidTokenResponse verifyReCaptcha(String token);
}
