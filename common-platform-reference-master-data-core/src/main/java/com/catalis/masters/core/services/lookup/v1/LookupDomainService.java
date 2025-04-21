package com.catalis.masters.core.services.lookup.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.lookup.v1.LookupDomainDTO;
import reactor.core.publisher.Mono;

public interface LookupDomainService {
    /**
     * Retrieves a paginated list of lookup domains based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of LookupDomainDTO objects
     */
    Mono<PaginationResponse<LookupDomainDTO>> listDomains(PaginationRequest paginationRequest);
    
    /**
     * Creates a new lookup domain based on the provided LookupDomainDTO.
     *
     * @param domainDto the lookup domain data transfer object containing details of the domain to be created
     * @return a Mono emitting the created LookupDomainDTO object
     */
    Mono<LookupDomainDTO> createDomain(LookupDomainDTO domainDto);
    
    /**
     * Retrieves the details of a lookup domain by its unique identifier.
     *
     * @param domainId the unique identifier of the lookup domain to retrieve
     * @return a Mono emitting the LookupDomainDTO containing details about the specified domain, or an empty Mono if not found
     */
    Mono<LookupDomainDTO> getDomain(Long domainId);
    
    /**
     * Updates the details of an existing lookup domain by its unique identifier.
     *
     * @param domainId the unique identifier of the lookup domain to be updated
     * @param domainDto the data transfer object containing the updated lookup domain details
     * @return a Mono emitting the updated LookupDomainDTO object if the update is successful
     */
    Mono<LookupDomainDTO> updateDomain(Long domainId, LookupDomainDTO domainDto);
    
    /**
     * Deletes a lookup domain identified by its unique identifier.
     *
     * @param domainId the unique identifier of the lookup domain to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteDomain(Long domainId);
}