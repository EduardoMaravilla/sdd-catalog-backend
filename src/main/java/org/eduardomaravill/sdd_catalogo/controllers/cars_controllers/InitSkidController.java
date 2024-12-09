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
import org.eduardomaravill.sdd_catalogo.dtos.car_dtos.InitSkidDto;
import org.eduardomaravill.sdd_catalogo.dtos.error.ApiResponseError;
import org.eduardomaravill.sdd_catalogo.services.cars_services.IInitSkidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/init-skids")
@Validated
@Tag(name = "Init Skid Controller", description = "Endpoints for managing init skid entities, including create, read, update, and delete operations.")
public class InitSkidController {

    private final IInitSkidService initSkidService;

    @Autowired
    public InitSkidController(IInitSkidService initSkidService) {
        this.initSkidService = initSkidService;
    }

    @GetMapping("/{idInitSkid}")
    @Operation(summary = "Get InitSkid",
            description = "Get a specific InitSkid by its unique identifier.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get Specific Car Data"},
            parameters = {
                    @Parameter(
                            name = "idInitSkid",
                            description = "Unique identifier of the InitSkid to be retrieved.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "InitSkid retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = InitSkidDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "InitSkid not found",
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
    public ResponseEntity<InitSkidDto> getInitSkid(@PathVariable Long idInitSkid){
        InitSkidDto initSkid = initSkidService.getInitSkid(idInitSkid);
        return ResponseEntity.ok(initSkid);
    }

    @GetMapping("")
    @Operation(summary = "Get All InitSkids",
            description = "Get all available InitSkids.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Get All Car Data"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of InitSkids",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = InitSkidDto.class)
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
    public ResponseEntity<List<InitSkidDto>> getAllInitSkids(){
        return ResponseEntity.ok(initSkidService.getAllInitSkids());
    }

    @PostMapping("")
    @Operation(summary = "Create InitSkid",
            description = "Create a new InitSkid.",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Create Car Data"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The details of the new InitSkid to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InitSkidDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The created InitSkid"
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
    public ResponseEntity<Void> createInitSkid(@RequestBody InitSkidDto initSkidDto){
        initSkidService.createInitSkid(initSkidDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idInitSkid}")
    @Operation(summary = "Update InitSkid",
            description = "Update InitSkid with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Update Car Data"},
            parameters = {
                    @Parameter(
                            name = "idInitSkid",
                            description = "The unique identifier of the InitSkid to update.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The updated details of the InitSkid",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = InitSkidDto.class)
                    )),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The updated InitSkid"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested InitSkid was not found.",
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
    public ResponseEntity<Void> updateInitSkid(@PathVariable Long idInitSkid, @RequestBody InitSkidDto initSkidDto){
        initSkidService.updateInitSkid(idInitSkid, initSkidDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idInitSkid}")
    @Operation(summary = "Delete InitSkid",
            description = "Delete InitSkid with specified id",
            security = {@SecurityRequirement(name = "Security Token")},
            tags = {"Delete Car Data"},
            parameters = {
                    @Parameter(
                            name = "idInitSkid",
                            description = "The unique identifier of the InitSkid to delete.",
                            required = true,
                            in = ParameterIn.PATH,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The InitSkid has been deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The requested InitSkid was not found.",
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
    public ResponseEntity<Void> deleteInitSkid(@PathVariable Long idInitSkid){
        initSkidService.deleteInitSkid(idInitSkid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
