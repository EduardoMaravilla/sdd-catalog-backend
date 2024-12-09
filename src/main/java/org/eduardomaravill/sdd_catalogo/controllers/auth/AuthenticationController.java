package org.eduardomaravill.sdd_catalogo.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.eduardomaravill.sdd_catalogo.dtos.auth.*;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.dtos.user_dtos.LogoutResponse;
import org.eduardomaravill.sdd_catalogo.services.auth.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Validated
@Tag(name = "Authentication Controller",
        description = "Endpoints for managing authentication, user contact requests, and data validation.")
public class AuthenticationController {

    private final IAuthenticationService authenticationService;


    @Autowired
    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/validate-token")
    @Operation(summary = "Validate JWT Token",
            description = "Validate JWT token and return true if valid",
            tags = {"Authentication"},
            security = {@SecurityRequirement(name = "Security Token")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Valid token",
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
    public ResponseEntity<ValidTokenResponse> validateToken(HttpServletRequest request) {
        ValidTokenResponse vTR = new ValidTokenResponse(authenticationService.validateToken(request));
        return ResponseEntity.ok(vTR);
    }

    @PostMapping("/authenticate")
    @Operation(summary = "Login User",
            description = "Authenticate a user and return the authentication token along with the user details",
            tags = {"Authentication"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Authentication request with username and password",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful authentication",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)

                            )),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid username or password",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Access denied",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )

                    )
            }
    )
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout User",
            description = "Invalidate the authenticated token",
            tags = {"Authentication"},
            security = {@SecurityRequirement(name = "Security Token")},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Logout successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LogoutResponse.class)
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
            })
    public ResponseEntity<LogoutResponse> logout(HttpServletRequest request) {
        authenticationService.logout(request);
        return ResponseEntity.ok(new LogoutResponse("Logout exitoso"));
    }

    @GetMapping("/profile")
    @Operation(summary = "Profile Information",
            description = "Returns information about the profile with the specified token",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Information about the profile with the specified token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserProfileResponse.class)
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
            })
    public ResponseEntity<UserProfileResponse> findMyProfile() {
        UserProfileResponse user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/contact-email")
    @Operation(summary = "Contact Email",
            description = "Sends email contact to developers",
            tags = {"Contact"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Email was sent to developers correctly",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Fail to send email",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            })
    public ResponseEntity<ValidTokenResponse> sendContactEmail(@RequestBody ContactFormRequest contactFormRequest) {
        ValidTokenResponse validTokenResponse = authenticationService.sendContactEmail(contactFormRequest);
        return ResponseEntity.ok(validTokenResponse);
    }

}
