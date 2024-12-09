package org.eduardomaravill.sdd_catalogo.controllers.cars_controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarConfigurationDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.dtos.user_car_dtos.FilterCar;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ICarConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/car-configurations")
@Validated
@Tag(name = "Car Configuration controller", description = "Endpoints for managing CarConfigurations entities, including create, read, update, and delete operations.")
public class CarConfigurationController {

    private final ICarConfigurationService carConfigurationService;

    @Autowired
    public CarConfigurationController(ICarConfigurationService carConfigurationService) {
        this.carConfigurationService = carConfigurationService;
    }

    @GetMapping("/{idCarConfiguration}")
    @Operation(summary = "Get Car Configuration",
            description = "Get a specific car configuration by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCarConfiguration",
                            description = "The unique identifier of the car configuration to retrieve.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful retrieval",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarConfigurationDto.class)
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
                            responseCode = "401",
                            description = "Invalid token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<CarConfigurationDto> getCarConfiguration(@PathVariable Long idCarConfiguration) {
        CarConfigurationDto carConfiguration = carConfigurationService.getCarConfiguration(idCarConfiguration);
        return ResponseEntity.ok(carConfiguration);
    }

    @GetMapping("")
    @Operation(summary = "Get All Car Configurations",
            description = "Get all car configurations.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of car configurations",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = CarConfigurationDto.class)
                                    )
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
    public ResponseEntity<List<CarConfigurationDto>> getAllCarConfigurations() {
        return ResponseEntity.ok(Collections.emptyList());
    }

    @PostMapping("")
    @Operation(summary = "Create Car Configuration",
            description = "Create a new car configuration.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Car configuration to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarConfigurationDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created car configuration"
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
    public ResponseEntity<Void> createCarConfiguration(@RequestBody CarConfigurationDto carConfigurationDto) {
        carConfigurationService.createCarConfiguration(carConfigurationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/search-filter")
    @Operation(
            summary = "Search Car Configurations",
            description = "Search car configurations based on given filters.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Search Car Data"},
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number (0-indexed)",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "0")
                    ),
                    @Parameter(
                            name = "size",
                            description = "Number of results per page",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "30")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Filter car configurations",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FilterCar.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of car configurations matching the search criteria",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Page.class)
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
    public ResponseEntity<Page<CarConfigurationDto>> searchCarConfigurations(Pageable pageable, @RequestBody FilterCar filterCar) {
        Page<CarConfigurationDto> carConfigurationDtoPage = carConfigurationService.searchCarConfigurations(filterCar, pageable);
        return ResponseEntity.ok(carConfigurationDtoPage);
    }

    @PutMapping("/{idCarConfiguration}")
    @Operation(summary = "Update Car Configuration",
            description = "Update an existing car configuration.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCarConfiguration",
                            description = "The unique identifier of the car configuration to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated car configuration data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarConfigurationDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated car configuration"
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
                            responseCode = "401",
                            description = "Invalid token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<Void> updateCarConfiguration(@PathVariable Long idCarConfiguration, @RequestBody CarConfigurationDto carConfigurationDto) {
        carConfigurationService.updateCarConfiguration(idCarConfiguration, carConfigurationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idCarConfiguration}")
    @Operation(summary = "Delete Car Configuration",
            description = "Delete an existing car configuration.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCarConfiguration",
                            description = "The unique identifier of the car configuration to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "The car configuration has been deleted"
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
                            responseCode = "401",
                            description = "Invalid token",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseError.class)
                            )
                    )
            }
    )
    public ResponseEntity<Void> deleteCarConfiguration(@PathVariable Long idCarConfiguration) {
        carConfigurationService.deleteCarConfiguration(idCarConfiguration);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
