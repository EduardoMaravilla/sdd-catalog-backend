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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.AuxiliarDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IAuxiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/auxiliaries")
@Validated
@Tag(name = "Auxiliary Controller", description = "Endpoints for managing Auxiliary entities, including create, read, update, and delete operations.")
public class AuxiliarController {


    private final IAuxiliarService auxiliarService;

    @Autowired
    public AuxiliarController(IAuxiliarService auxiliarService) {
        this.auxiliarService = auxiliarService;
    }

    @GetMapping("/{idAuxiliary}")
    @Operation(summary = "Get auxiliary",
            description = "Get a specific auxiliary by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idAuxiliary",
                            description = "The unique identifier of the auxiliary to retrieve.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Auxiliary retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuxiliarDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested auxiliary was not found.",
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
    public ResponseEntity<AuxiliarDto> getAuxiliar(@PathVariable Long idAuxiliary) {
        AuxiliarDto auxiliar = auxiliarService.getAuxiliar(idAuxiliary);
        return ResponseEntity.ok(auxiliar);
    }

    @GetMapping("")
    @Operation(summary = "Get all auxiliaries",
            description = "Get all available auxiliaries.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of auxiliaries",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = AuxiliarDto.class)
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
    public ResponseEntity<List<AuxiliarDto>> getAllAuxiliaries() {
        return ResponseEntity.ok(auxiliarService.getAllAuxiliaries());
    }

    @PostMapping("")
    @Operation(summary = "Create Auxiliary",
            description = "Create a new Auxiliary.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new Auxiliary to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuxiliarDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created Auxiliary"
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
    public ResponseEntity<Void> createAuxiliar(@RequestBody AuxiliarDto auxiliarDto) {
        auxiliarService.createAuxiliar(auxiliarDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idAuxiliary}")
    @Operation(summary = "Update Auxiliary",
            description = "Update Auxiliary with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idAuxiliary",
                            description = "The unique identifier of the auxiliary to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the auxiliary",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuxiliarDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated auxiliary"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested auxiliary was not found.",
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
    public ResponseEntity<Void> updateAuxiliar(@PathVariable Long idAuxiliary, @RequestBody AuxiliarDto auxiliarDto) {
        auxiliarService.updateAuxiliar(idAuxiliary, auxiliarDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idAuxiliary}")
    @Operation(summary = "Delete Auxiliary",
            description = "Delete Auxiliary with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idAuxiliary",
                            description = "The unique identifier of the Auxiliary to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Auxiliary has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Auxiliary was not found.",
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
    public ResponseEntity<Void> deleteAuxiliar(@PathVariable Long idAuxiliary) {
        auxiliarService.deleteAuxiliar(idAuxiliary);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
