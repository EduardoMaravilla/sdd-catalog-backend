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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITurboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/turbos")
@Validated
@Tag(name = "Turbo Controller", description = "Endpoints for managing Turbo entities, including create, read, update, and delete operations.")
public class TurboController {

    private final ITurboService turboService;

    @Autowired
    public TurboController(ITurboService turboService) {
        this.turboService = turboService;
    }

    @GetMapping("/{idTurbo}")
    @Operation(summary = "Get Turbo",
            description = "Get a specific Turbo by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurbo",
                            description = "Unique identifier of the Turbo to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Turbo retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TurboDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Turbo not found",
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
    public ResponseEntity<TurboDto> getTurbo(@PathVariable Long idTurbo){
        TurboDto turbo = turboService.getTurbo(idTurbo);
        return ResponseEntity.ok(turbo);
    }

    @GetMapping("")
    @Operation(summary = "Get All Turbos",
            description = "Get all available Turbos.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of Turbos",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = TurboDto.class)
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
    public ResponseEntity<List<TurboDto>> getAllTurbos(){
        return ResponseEntity.ok(turboService.getAllTurbos());
    }

    @PostMapping("")
    @Operation(summary = "Create Turbo",
            description = "Create a new Turbo.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new Turbo to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TurboDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created Turbo"
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
    public ResponseEntity<Void> createTurbo(@RequestBody TurboDto turboDto) {
        turboService.createTurbo(turboDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idTurbo}")
    @Operation(summary = "Update Turbo",
            description = "Update Turbo with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurbo",
                            description = "The unique identifier of the Turbo to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the Turbo",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TurboDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated Turbo"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Turbo was not found.",
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
    public ResponseEntity<Void> updateTurbo(@PathVariable Long idTurbo, @RequestBody TurboDto turboDto) {
        turboService.updateTurbo(idTurbo, turboDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idTurbo}")
    @Operation(summary = "Delete Turbo",
            description = "Delete Turbo with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurbo",
                            description = "The unique identifier of the Turbo to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Turbo has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Turbo was not found.",
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
    public ResponseEntity<Void> deleteTurbo(@PathVariable Long idTurbo) {
        turboService.deleteTurbo(idTurbo);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
