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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.StreetTypeDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IStreetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/street-types")
@Validated
@Tag(name = "Street Type Controller", description = "Endpoints for managing Street Type entities, including create, read, update, and delete operations.")
public class StreetTypeController {

    private final IStreetTypeService streetTypeService;

    @Autowired
    public StreetTypeController(IStreetTypeService streetTypeService) {
        this.streetTypeService = streetTypeService;
    }

    @GetMapping("/{idStreetType}")
    @Operation(summary = "Get StreetType",
            description = "Get a specific StreetType by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idStreetType",
                            description = "Unique identifier of the StreetType to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "StreetType retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = StreetTypeDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "StreetType not found",
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
    public ResponseEntity<StreetTypeDto> getStreetType(@PathVariable Long idStreetType){
        StreetTypeDto streetTypeDto = streetTypeService.getStreetType(idStreetType);
        return ResponseEntity.ok(streetTypeDto);
    }

    @GetMapping("")
    @Operation(summary = "Get All StreetTypes",
            description = "Get all available StreetTypes.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of StreetTypes",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = StreetTypeDto.class)
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
    public ResponseEntity<List<StreetTypeDto>> getAllStreetTypes(){
        return ResponseEntity.ok(streetTypeService.getAllStreetTypes());
    }

    @PostMapping("")
    @Operation(summary = "Create StreetType",
            description = "Create a new StreetType.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new StreetType to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StreetTypeDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created StreetType"
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
    public ResponseEntity<Void> createStreetType(@RequestBody StreetTypeDto streetTypeDto){
        streetTypeService.createStreetType(streetTypeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idStreetType}")
    @Operation(summary = "Update StreetType",
            description = "Update StreetType with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idStreetType",
                            description = "The unique identifier of the StreetType to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the StreetType",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = StreetTypeDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated StreetType"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested StreetType was not found.",
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
    public ResponseEntity<Void> updateStreetType(@PathVariable Long idStreetType, @RequestBody StreetTypeDto streetTypeDto){
        streetTypeService.updateStreetType(idStreetType, streetTypeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idStreetType}")
    @Operation(summary = "Delete StreetType",
            description = "Delete StreetType with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idStreetType",
                            description = "The unique identifier of the StreetType to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The StreetType has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested StreetType was not found.",
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
    public ResponseEntity<Void> deleteStreetType(@PathVariable Long idStreetType){
        streetTypeService.deleteStreetType(idStreetType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
