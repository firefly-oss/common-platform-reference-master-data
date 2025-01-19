package com.catalis.masters.web.controllers.branch.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.services.branch.v1.BranchServiceImpl;
import com.catalis.masters.interfaces.dtos.branch.v1.BranchDTO;
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

@Tag(name = "Branches", description = "APIs for managing branches")
@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    @Autowired
    private BranchServiceImpl service;

    @Operation(summary = "List Branches", description = "Retrieve a paginated list of branches.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved list of branches",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaginationResponse.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<BranchDTO>>> listBranches(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listBranches(paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Create Branch", description = "Create a new branch.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Branch created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BranchDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content
            )
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BranchDTO>> createBranch(
            @RequestBody BranchDTO branchDto
    ) {
        return service.createBranch(branchDto)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get Branch by ID", description = "Retrieve a specific branch by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Branch retrieved successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BranchDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Branch not found",
                    content = @Content
            )
    })
    @GetMapping(value = "/{branchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BranchDTO>> getBranch(
            @Parameter(in = ParameterIn.PATH, description = "ID of the branch", required = true)
            @PathVariable Long branchId
    ) {
        return service.getBranch(branchId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update Branch", description = "Update an existing branch by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Branch updated successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BranchDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Branch not found",
                    content = @Content
            )
    })
    @PutMapping(value = "/{branchId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BranchDTO>> updateBranch(
            @Parameter(in = ParameterIn.PATH, description = "ID of the branch", required = true)
            @PathVariable Long branchId,
            @RequestBody BranchDTO branchDto
    ) {
        return service.updateBranch(branchId, branchDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete Branch", description = "Delete a specific branch by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Branch deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Branch not found")
    })
    @DeleteMapping(value = "/{branchId}")
    public Mono<ResponseEntity<Void>> deleteBranch(
            @Parameter(in = ParameterIn.PATH, description = "ID of the branch", required = true)
            @PathVariable Long branchId
    ) {
        return service.deleteBranch(branchId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}

