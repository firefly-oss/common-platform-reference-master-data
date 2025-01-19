package com.catalis.masters.web.controllers.holiday.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.services.holiday.v1.HolidayServiceImpl;
import com.catalis.masters.interfaces.dtos.holiday.v1.HolidayDTO;
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

@Tag(name = "Holidays", description = "APIs for managing holidays")
@RestController
@RequestMapping("/api/v1/holidays")
public class HolidayController {

    @Autowired
    private HolidayServiceImpl service;

    @Operation(summary = "List Holidays", description = "Retrieve a paginated list of holidays.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of holidays",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<HolidayDTO>>> listHolidays(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listHolidays(paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Create Holiday", description = "Create a new holiday.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Holiday created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HolidayDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HolidayDTO>> createHoliday(
            @RequestBody HolidayDTO holidayDto
    ) {
        return service.createHoliday(holidayDto)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get Holiday by ID", description = "Retrieve a specific holiday by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Holiday retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HolidayDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Holiday not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/{holidayId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HolidayDTO>> getHoliday(
            @Parameter(in = ParameterIn.PATH, description = "ID of the holiday", required = true)
            @PathVariable Long holidayId
    ) {
        return service.getHoliday(holidayId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update Holiday", description = "Update an existing holiday by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Holiday updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HolidayDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Holiday not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{holidayId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<HolidayDTO>> updateHoliday(
            @Parameter(in = ParameterIn.PATH, description = "ID of the holiday", required = true)
            @PathVariable Long holidayId,
            @RequestBody HolidayDTO holidayDto
    ) {
        return service.updateHoliday(holidayId, holidayDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Holiday", description = "Delete a specific holiday by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Holiday deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Holiday not found")
    })
    @DeleteMapping(value = "/{holidayId}")
    public Mono<ResponseEntity<Void>> deleteHoliday(
            @Parameter(in = ParameterIn.PATH, description = "ID of the holiday", required = true)
            @PathVariable Long holidayId
    ) {
        return service.deleteHoliday(holidayId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}

