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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.TurboTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ITurboTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/turbo-types")
@Validated
@Tag(name = "Turbo Type Controller", description = "Endpoints for managing Turbo Type entities, including create, read, update, and delete operations.")
public class TurboTypeController {

    private final ITurboTypeService turboTypeService;

    @Autowired
    public TurboTypeController(ITurboTypeService turboTypeService) {
        this.turboTypeService = turboTypeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get TurboType",
            description = "Get a specific TurboType by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurboType",
                            description = "Unique identifier of the TurboType to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "TurboType retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TurboTypeDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "TurboType not found",
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
    public ResponseEntity<TurboTypeDto> getTurboTypeDto(@PathVariable Long id) {
        TurboTypeDto turboType = turboTypeService.getTurboType(id);
        return ResponseEntity.ok(turboType);
    }

    @GetMapping("")
    @Operation(summary = "Get All TurboTypes",
            description = "Get all available TurboTypes.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of TurboTypes",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = TurboTypeDto.class)
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
    public ResponseEntity<List<TurboTypeDto>> getAllTurboTypes() {
        return ResponseEntity.ok(turboTypeService.getAllTurboTypes());
    }

    @PostMapping("")
    @Operation(summary = "Create TurboType",
            description = "Create a new TurboType.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new TurboType to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TurboTypeDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created TurboType"
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
    public ResponseEntity<Void> createTurboType(@RequestBody TurboTypeDto turboTypeDto) {
        turboTypeService.createTurboType(turboTypeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update TurboType",
            description = "Update TurboType with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurboType",
                            description = "The unique identifier of the TurboType to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the TurboType",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TurboTypeDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated TurboType"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested TurboType was not found.",
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
    public ResponseEntity<Void> updateTurboType(@PathVariable Long id, @RequestBody TurboTypeDto turboTypeDto) {
        turboTypeService.updateTurboType(id, turboTypeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete TurboType",
            description = "Delete TurboType with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idTurboType",
                            description = "The unique identifier of the TurboType to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The TurboType has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested TurboType was not found.",
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
    public ResponseEntity<Void> deleteTurboType(@PathVariable Long id) {
        turboTypeService.deleteTurboType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
