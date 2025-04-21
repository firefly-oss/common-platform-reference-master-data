package com.catalis.masters.core.services.legal.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.legal.v1.LegalFormDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LegalFormService {
    /**
     * Retrieves a paginated list of legal forms based on the provided pagination request.
     *
     * @param paginationRequest the pagination request containing page number, size, and sorting options
     * @return a Mono emitting a PaginationResponse containing a list of LegalFormDTO objects
     */
    Mono<PaginationResponse<LegalFormDTO>> listLegalForms(PaginationRequest paginationRequest);
    
    /**
     * Retrieves all legal forms for a specific country.
     *
     * @param countryId the unique identifier of the country to retrieve legal forms for
     * @return a Flux emitting LegalFormDTO objects for the specified country
     */
    Flux<LegalFormDTO> getLegalFormsByCountry(Long countryId);
    
    /**
     * Creates a new legal form based on the provided LegalFormDTO.
     *
     * @param legalFormDto the legal form data transfer object containing details of the legal form to be created
     * @return a Mono emitting the created LegalFormDTO object
     */
    Mono<LegalFormDTO> createLegalForm(LegalFormDTO legalFormDto);
    
    /**
     * Retrieves the details of a legal form by its unique identifier.
     *
     * @param legalFormId the unique identifier of the legal form to retrieve
     * @return a Mono emitting the LegalFormDTO containing details about the specified legal form, or an empty Mono if not found
     */
    Mono<LegalFormDTO> getLegalForm(Long legalFormId);
    
    /**
     * Updates the details of an existing legal form by its unique identifier.
     *
     * @param legalFormId the unique identifier of the legal form to be updated
     * @param legalFormDto the data transfer object containing the updated legal form details
     * @return a Mono emitting the updated LegalFormDTO object if the update is successful
     */
    Mono<LegalFormDTO> updateLegalForm(Long legalFormId, LegalFormDTO legalFormDto);
    
    /**
     * Deletes a legal form identified by its unique identifier.
     *
     * @param legalFormId the unique identifier of the legal form to delete
     * @return a Mono signaling completion of the delete operation
     */
    Mono<Void> deleteLegalForm(Long legalFormId);
}