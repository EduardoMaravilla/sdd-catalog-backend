package org.eduardomaravill.sdd_catalogo.services.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.eduardomaravill.sdd_catalogo.dtos.auth.*;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.NewUpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UserSaveDto;


public interface IAuthenticationService {
    ValidTokenResponse registerOneRacer(UserSaveDto newUser);
    AuthenticationResponse login(AuthenticationRequest autRequest);
    Boolean validateToken(HttpServletRequest request);
    UserProfileResponse findLoggedInUser();
    void logout(HttpServletRequest request);
    NewUserProfileResponse updateProfileOneRacer(HttpServletRequest request,UserEditProfileRequest userEditProfileRequest);
    NewUserProfileResponse updateEmailVerified(HttpServletRequest request);
    ValidTokenResponse resetPassword(HttpServletRequest request);
    ValidTokenResponse updatePassword(HttpServletRequest request, UpdatePasswordRequest updatePasswordRequest);
    ValidTokenResponse verifyReCaptchaToken(String token);
    NewUserProfileResponse profilePasswordUpdate(HttpServletRequest request, NewUpdatePasswordRequest newUpdatePasswordRequest);

    ValidTokenResponse sendContactEmail(ContactFormRequest contactFormRequest);
}
