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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.CarDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
@Validated
@Tag(name = "Car Controller", description = "Endpoints for managing Car entities, including create, read, update, and delete operations.")
public class CarController {

    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{idCar}")
    @Operation(summary = "Get Car",
            description = "Get a specific Car by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCar",
                            description = "Unique identifier of the Car to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Car retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Car not found",
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
    public ResponseEntity<CarDto> getCar(@PathVariable Long idCar) {
        CarDto car = carService.getCar(idCar);
        return ResponseEntity.ok(car);
    }

    @GetMapping("")
    @Operation(summary = "Get All Cars",
            description = "Get all available Cars.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of Cars",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = CarDto.class)
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
    public ResponseEntity<List<CarDto>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping("")
    @Operation(summary = "Create Car",
            description = "Create a new car.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new car to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created car"
                    )
            }
    )
    public ResponseEntity<Void> createCar(@RequestBody CarDto carDto) {
        carService.createCar(carDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idCar}")
    @Operation(summary = "Update Car",
            description = "Update an existing car.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCar",
                            description = "Unique identifier of the car to be updated",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the car",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated car"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Car not found",
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
    public ResponseEntity<Void> updateCar(@PathVariable Long idCar, @RequestBody CarDto carDto) {
        carService.updateCar(idCar, carDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idCar}")
    @Operation(summary = "Delete Car",
            description = "Delete an existing car.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idCar",
                            description = "Unique identifier of the car to be deleted",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The deleted car"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Car not found",
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
    public ResponseEntity<Void> deleteCar(@PathVariable Long idCar) {
        carService.deleteCar(idCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
