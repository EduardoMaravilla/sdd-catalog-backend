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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.GearDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IGearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/gears")
@Validated
@Tag(name = "Gear Controller", description = "Endpoints for managing Gear entities, including create, read, update, and delete operations.")
public class GearController {

    private final IGearService gearService;

    @Autowired
    public GearController(IGearService gearService) {
        this.gearService = gearService;
    }

    @GetMapping("/{idGear}")
    @Operation(summary = "Get Gear",
            description = "Get a specific Gear by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idGear",
                            description = "Unique identifier of the Gear to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Gear retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = GearDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Gear not found",
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
    public ResponseEntity<GearDto> getGear(@PathVariable Long idGear) {
        GearDto gear = gearService.getGear(idGear);
        return ResponseEntity.ok(gear);
    }

    @GetMapping("")
    @Operation(summary = "Get All Gears",
            description = "Get all available Gears.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of Gears",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = GearDto.class)
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
    public ResponseEntity<List<GearDto>> getAllGears() {
        return ResponseEntity.ok(gearService.getAllGears());
    }

    @PostMapping("")
    @Operation(summary = "Create Gear",
            description = "Create a new Gear.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new Gear to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GearDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created Gear"
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
    public ResponseEntity<Void> createGear(@RequestBody GearDto gearDto) {
        gearService.createGear(gearDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idGear}")
    @Operation(summary = "Update Gear",
            description = "Update Gear with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idGear",
                            description = "The unique identifier of the Gear to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the Gear",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GearDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated Gear"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Gear was not found.",
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
    public ResponseEntity<Void> updateGear(@PathVariable Long idGear, @RequestBody GearDto gearDto) {
        gearService.updateGear(idGear, gearDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idGear}")
    @Operation(summary = "Delete Gear",
            description = "Delete Gear with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idGear",
                            description = "The unique identifier of the Gear to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The Gear has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested Gear was not found.",
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
    public ResponseEntity<Void> deleteGear(@PathVariable Long idGear) {
        gearService.deleteGear(idGear);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
