package org.eduardomaravill.sdd_catalogo.services.users_services;

import org.eduardomaravill.sdd_catalogo.dtos.auth.ContactFormRequest;
import org.eduardomaravill.sdd_catalogo.models.users_models.User;

public interface IEmailService {
    void sendEmailVerification(User user, String jwtToken);

    void sendEmailPasswordReset(User user, String jwtToken);

    void sendEmailWelcome(User user);

    void  sendEmailResetPasswordSuccess(User user);

    void sendContactEmail(ContactFormRequest contactFormRequest);
}