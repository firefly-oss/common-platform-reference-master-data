package com.catalis.masters.core.services.title.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.title.v1.TitleMasterDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing Title Master data.
 */
public interface TitleMasterService {

    /**
     * Retrieves a paginated list of titles based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of TitleMasterDTO objects
     */
    Mono<PaginationResponse<TitleMasterDTO>> listTitles(PaginationRequest paginationRequest);

    /**
     * Creates a new title master record based on the provided TitleMasterDTO.
     *
     * @param titleDto the DTO containing details of the title to be created
     * @return a Mono emitting the created TitleMasterDTO object
     */
    Mono<TitleMasterDTO> createTitle(TitleMasterDTO titleDto);

    /**
     * Retrieves the details of a title by its unique identifier.
     *
     * @param titleId the unique identifier of the title to retrieve
     * @return a Mono emitting the TitleMasterDTO containing details about the specified title, or an empty Mono if not found
     */
    Mono<TitleMasterDTO> getTitle(Long titleId);

    /**
     * Updates the details of an existing title by its unique identifier.
     *
     * @param titleId  the unique identifier of the title to be updated
     * @param titleDto the DTO containing the updated title details
     * @return a Mono emitting the updated TitleMasterDTO object if the update is successful
     */
    Mono<TitleMasterDTO> updateTitle(Long titleId, TitleMasterDTO titleDto);

    /**
     * Deletes a title identified by its unique identifier.
     *
     * @param titleId the unique identifier of the title to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteTitle(Long titleId);
}