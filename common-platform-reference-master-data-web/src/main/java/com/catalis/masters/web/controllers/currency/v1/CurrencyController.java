package com.catalis.masters.web.controllers.currency.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.services.currency.v1.CurrencyServiceImpl;
import com.catalis.masters.interfaces.dtos.currency.v1.CurrencyDTO;
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

@Tag(name = "Currencies", description = "APIs for managing currencies")
@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyServiceImpl service;

    @Operation(summary = "List Currencies", description = "Retrieve a paginated list of currencies.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of currencies",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CurrencyDTO>>> listCurrencies(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listCurrencies(paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Create Currency", description = "Create a new currency.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Currency created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CurrencyDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CurrencyDTO>> createCurrency(
            @RequestBody CurrencyDTO currencyDto
    ) {
        return service.createCurrency(currencyDto)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get Currency by ID", description = "Retrieve a specific currency by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Currency retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CurrencyDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Currency not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/{currencyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CurrencyDTO>> getCurrency(
            @Parameter(in = ParameterIn.PATH, description = "ID of the currency", required = true)
            @PathVariable Long currencyId
    ) {
        return service.getCurrency(currencyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update Currency", description = "Update an existing currency by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Currency updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CurrencyDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Currency not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{currencyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CurrencyDTO>> updateCurrency(
            @Parameter(in = ParameterIn.PATH, description = "ID of the currency", required = true)
            @PathVariable Long currencyId,
            @RequestBody CurrencyDTO currencyDto
    ) {
        return service.updateCurrency(currencyId, currencyDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Currency", description = "Delete a specific currency by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Currency deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Currency not found")
    })
    @DeleteMapping(value = "/{currencyId}")
    public Mono<ResponseEntity<Void>> deleteCurrency(
            @Parameter(in = ParameterIn.PATH, description = "ID of the currency", required = true)
            @PathVariable Long currencyId
    ) {
        return service.deleteCurrency(currencyId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}

