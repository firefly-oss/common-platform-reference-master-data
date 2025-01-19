package com.catalis.masters.web.controllers.country.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.services.country.v1.CountryServiceImpl;
import com.catalis.masters.interfaces.dtos.country.v1.CountryDTO;
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

@Tag(name = "Countries", description = "APIs for managing countries")
@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryServiceImpl service;

    @Operation(summary = "List Countries", description = "Retrieve a paginated list of countries.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of countries",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CountryDTO>>> listCountries(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listCountries(paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Create Country", description = "Create a new country.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Country created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CountryDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CountryDTO>> createCountry(
            @RequestBody CountryDTO countryDto
    ) {
        return service.createCountry(countryDto)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get Country by ID", description = "Retrieve a specific country by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Country retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CountryDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/{countryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CountryDTO>> getCountry(
            @Parameter(in = ParameterIn.PATH, description = "ID of the country", required = true)
            @PathVariable Long countryId
    ) {
        return service.getCountry(countryId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update Country", description = "Update an existing country by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Country updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CountryDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Country not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{countryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CountryDTO>> updateCountry(
            @Parameter(in = ParameterIn.PATH, description = "ID of the country", required = true)
            @PathVariable Long countryId,
            @RequestBody CountryDTO countryDto
    ) {
        return service.updateCountry(countryId, countryDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Country", description = "Delete a specific country by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Country deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Country not found")
    })
    @DeleteMapping(value = "/{countryId}")
    public Mono<ResponseEntity<Void>> deleteCountry(
            @Parameter(in = ParameterIn.PATH, description = "ID of the country", required = true)
            @PathVariable Long countryId
    ) {
        return service.deleteCountry(countryId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}