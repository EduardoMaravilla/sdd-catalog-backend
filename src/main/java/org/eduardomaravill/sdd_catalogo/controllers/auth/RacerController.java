package org.eduardomaravill.sdd_catalogo.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.eduardomaravill.sdd_catalogo.dtos.auth.NewUserProfileResponse;
import org.eduardomaravill.sdd_catalogo.dtos.auth.UserEditProfileRequest;
import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.NewUpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UpdatePasswordRequest;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.UserSaveDto;
import org.eduardomaravill.sdd_catalogo.services.auth.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/racers")
@Validated
@Tag(name = "Racer Controller", description = "Endpoints for managing racer-related user data, including profile and preferences.")
@Log4j2
public class RacerController {

    private final IAuthenticationService authenticationService;

    @Autowired
    public RacerController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a user",
            description = "Registers a new user and send a notification to email",
            tags = {"User"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requests user information for registration",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserSaveDto.class)
                    )

            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful registration",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)

                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "User already exist or data not valid",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )

                    )
            }

    )
    public ResponseEntity<ValidTokenResponse> registerOneRacer(@RequestBody UserSaveDto newUser) {
        log.info("New User Registration");
        ValidTokenResponse validTokenResponse = authenticationService.registerOneRacer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(validTokenResponse);
    }

    @PostMapping("/update-profile")
    @Operation(summary = "Update user profile",
            description = "Update information about the user",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requests user information for update",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserEditProfileRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = NewUserProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid token or data not valid",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<NewUserProfileResponse> updateProfile(HttpServletRequest request, @RequestBody UserEditProfileRequest userEditProfileRequest) {
        NewUserProfileResponse newUserProfileResponse = authenticationService.updateProfileOneRacer(request, userEditProfileRequest);
        return ResponseEntity.ok(newUserProfileResponse);
    }

    @PostMapping("/profile-verified")
    @Operation(summary = "Validates user email",
            description = "Updates user email to verified",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = NewUserProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<NewUserProfileResponse> updateProfileVerifier(HttpServletRequest request) {
        NewUserProfileResponse newUserProfileResponse = authenticationService.updateEmailVerified(request);
        return ResponseEntity.ok(newUserProfileResponse);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset Password",
            description = "Resets password if user forgot it",
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful, email was sent successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Invalid email address",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }

    )
    public ResponseEntity<ValidTokenResponse> resetPassword(HttpServletRequest request) {
        ValidTokenResponse validTokenResponse = authenticationService.resetPassword(request);
        return ResponseEntity.ok(validTokenResponse);
    }

    @PostMapping("/update-password")
    @Operation(summary = "Update Password",
            description = "Update the password after the user receives the authorization token in the email",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requests user information for update password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdatePasswordRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update password",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }

    )
    public ResponseEntity<ValidTokenResponse> updatePassword(HttpServletRequest request, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
        ValidTokenResponse validTokenResponse = authenticationService.updatePassword(request, updatePasswordRequest);
        return ResponseEntity.ok(validTokenResponse);
    }

    @PostMapping("/verify-recaptcha-token")
    @Operation(summary = "Verify HCaptcha Token",
            description = "Verify HCaptcha Token with Api HCaptcha",
            tags = {"HCaptcha"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requests HCaptcha Token",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful verification",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "HCaptcha token verification failed",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<ValidTokenResponse> verifyReCaptchaToken(@RequestBody Map<String, String> requestBody) {
        String token = requestBody.get("token");
        ValidTokenResponse response = authenticationService.verifyReCaptchaToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile-password-update")
    @Operation(summary = "Update profile",
            description = "Update password profile information",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Requests user information for update password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NewUpdatePasswordRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update password profile",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = NewUserProfileResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid token or invalid data",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )

            }
    )
    public ResponseEntity<NewUserProfileResponse> profilePasswordUpdate(HttpServletRequest request, @RequestBody NewUpdatePasswordRequest newUpdatePasswordRequest) {
        NewUserProfileResponse newUserProfileResponse = authenticationService.profilePasswordUpdate(request, newUpdatePasswordRequest);
        return ResponseEntity.ok(newUserProfileResponse);
    }

}
