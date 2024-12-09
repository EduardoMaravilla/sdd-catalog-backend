package org.eduardomaravill.sdd_catalogo.controllers.user_car_controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eduardomaravill.sdd_catalogo.dtos.auth.ValidTokenResponse;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarConfigurationDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.user_car_services.IUserCarConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/racer-car-configurations")
@Validated
@Tag(name = "Racer Car Controller", description = "Endpoints for managing user car configurations, including create, read, update, and delete operations.")
public class RacerCarController {


    private final IUserCarConfigurationService userCarConfigurationService;

    @Autowired
    public RacerCarController(IUserCarConfigurationService userCarConfigurationService) {
        this.userCarConfigurationService = userCarConfigurationService;
    }

    @PostMapping("/save-car-configuration")
    @Operation(summary = "Save the configuration",
            description = "Saves the  car configuration of the user",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User Car"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Car configuration to save",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarConfigurationDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successful creation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)

                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }

    )
    public ResponseEntity<ValidTokenResponse> createRacerCarConfiguration(@RequestBody CarConfigurationDto carConfigurationDto) {
        ValidTokenResponse validTokenResponse = userCarConfigurationService.createRacerCarConfiguration(carConfigurationDto);
        return new ResponseEntity<>(validTokenResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update-car-configuration/{idCarConfiguration}")
    @Operation(summary = "Update configuration",
            description = "Update car configuration",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User Car"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Car configuration to update",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarConfigurationDto.class)
                    )
            ),
            parameters = {
                    @Parameter(
                            name = "idCarConfiguration",
                            description = "ID of the car configuration to update",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful update",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Car configuration not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<ValidTokenResponse> updateRacerCarConfiguration(@RequestBody CarConfigurationDto carConfigurationDto,
                                                                          @PathVariable Long idCarConfiguration) {
        ValidTokenResponse validTokenResponse = userCarConfigurationService.updateRacerCarConfiguration(idCarConfiguration, carConfigurationDto);
        return new ResponseEntity<>(validTokenResponse, HttpStatus.OK);
    }

    @PostMapping("/racer-all-cars")
    @Operation(
            summary = "Get All Car Configurations",
            description = "Retrieve all car configurations associated with the user",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User Car"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval of car configurations",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = CarConfigurationDto.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<List<CarConfigurationDto>> getRacerCarConfigurations() {
        return ResponseEntity.ok(userCarConfigurationService.getAllRacerCarConfigurations());
    }


    @DeleteMapping("/delete-car-configuration/{idCarConfiguration}")
    @Operation(summary = "Delete configuration",
            description = "Delete car configuration",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"User Car"},
            parameters = {
                    @Parameter(
                            name = "idCarConfiguration",
                            description = "ID of the car configuration to delete",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful deletion",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ValidTokenResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Car configuration not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<ValidTokenResponse> deleteRacerCarConfiguration(@PathVariable Long idCarConfiguration) {
        ValidTokenResponse validTokenResponse = userCarConfigurationService.deleteRacerCarConfiguration(idCarConfiguration);
        return new ResponseEntity<>(validTokenResponse, HttpStatus.OK);
    }
}
