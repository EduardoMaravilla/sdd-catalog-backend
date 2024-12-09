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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.SuspensionDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.ISuspensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/suspensions")
@Validated
@Tag(name = "Suspension Controller", description = "Endpoints for managing Suspension entities, including create, read, update, and delete operations.")
public class SuspensionController {

    private final ISuspensionService suspensionService;

    @Autowired
    public SuspensionController(ISuspensionService suspensionService) {
        this.suspensionService = suspensionService;
    }

    @GetMapping("/{idSuspension}")
    @Operation(summary = "Get Suspension",
            description = "Get a specific Suspension by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idSuspension",
                            description = "Unique identifier of the Suspension to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Suspension retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SuspensionDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Suspension not found",
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
    public ResponseEntity<SuspensionDto> getSuspension(@PathVariable Long idSuspension){
        SuspensionDto suspension = suspensionService.getSuspension(idSuspension);
        return ResponseEntity.ok(suspension);
    }

    @GetMapping("")
    @Operation(summary = "Get All Suspensions",
            description = "Get all available Suspensions.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of Suspensions",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = SuspensionDto.class)
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
    public ResponseEntity<List<SuspensionDto>> getAllSuspensions(){
        return ResponseEntity.ok(suspensionService.getAllSuspensions());
    }

    @PostMapping("")
    @Operation(summary = "Create Suspension",
            description = "Create a new Suspension.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new Suspension to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuspensionDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created Suspension"
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
    public ResponseEntity<Void> createSuspension(@RequestBody SuspensionDto suspensionDto){
        suspensionService.createSuspension(suspensionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idSuspension}")
    @Operation(summary = "Update Suspension",
            description = "Update Suspension with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idSuspension",
                            description = "The unique identifier of the Suspension to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the Suspension",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SuspensionDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated Suspension"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Suspension was not found.",
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
    public ResponseEntity<Void> updateSuspension(@PathVariable Long idSuspension, @RequestBody SuspensionDto suspensionDto){
        suspensionService.updateSuspension(idSuspension, suspensionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idSuspension}")
    @Operation(summary = "Delete Suspension",
            description = "Delete Suspension with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idSuspension",
                            description = "The unique identifier of the Suspension to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Suspension has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Suspension was not found.",
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
    public ResponseEntity<Void> deleteSuspension(@PathVariable Long idSuspension){
        suspensionService.deleteSuspension(idSuspension);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
