package com.firefly.masters.web.controllers.locale.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.services.locale.v1.LanguageLocaleServiceImpl;
import com.firefly.masters.interfaces.dtos.locale.v1.LanguageLocaleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "LanguageLocale", description = "APIs for managing language/locale data")
@RestController
@RequestMapping("/api/v1/language-locales")
public class LanguageLocaleController {

    @Autowired
    private LanguageLocaleServiceImpl service;

    @Operation(summary = "List LanguageLocales", description = "Retrieve a paginated list of language/locale records.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of language locales",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<LanguageLocaleDTO>>> listLanguageLocales(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listLanguageLocales(paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Create LanguageLocale", description = "Create a new language/locale entry.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "LanguageLocale entry created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LanguageLocaleDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LanguageLocaleDTO>> createLanguageLocale(
            @RequestBody LanguageLocaleDTO dto
    ) {
        return service.createLanguageLocale(dto)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get LanguageLocale by ID", description = "Retrieve a specific language/locale record by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "LanguageLocale retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LanguageLocaleDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "LanguageLocale not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LanguageLocaleDTO>> getLanguageLocale(
            @Parameter(in = ParameterIn.PATH, description = "ID of the languageLocale", required = true)
            @PathVariable Long id
    ) {
        return service.getLanguageLocale(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update LanguageLocale", description = "Update an existing language/locale record by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "LanguageLocale updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LanguageLocaleDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "LanguageLocale not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<LanguageLocaleDTO>> updateLanguageLocale(
            @Parameter(in = ParameterIn.PATH, description = "ID of the languageLocale", required = true)
            @PathVariable Long id,
            @RequestBody LanguageLocaleDTO dto
    ) {
        return service.updateLanguageLocale(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete LanguageLocale", description = "Delete a specific language/locale record by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "LanguageLocale entry deleted successfully"),
            @ApiResponse(responseCode = "404", description = "LanguageLocale not found")
    })
    @DeleteMapping(value = "/{id}")
    public Mono<ResponseEntity<Void>> deleteLanguageLocale(
            @Parameter(in = ParameterIn.PATH, description = "ID of the languageLocale", required = true)
            @PathVariable Long id
    ) {
        return service.deleteLanguageLocale(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}