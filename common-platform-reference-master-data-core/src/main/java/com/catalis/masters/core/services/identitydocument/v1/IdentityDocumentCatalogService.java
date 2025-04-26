package com.catalis.masters.core.services.identitydocument.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCatalogDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing identity document catalog operations.
 */
public interface IdentityDocumentCatalogService {

    /**
     * List all identity documents with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity documents
     */
    Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocuments(PaginationRequest paginationRequest);

    /**
     * List identity documents by category with pagination.
     *
     * @param categoryId the ID of the identity document category
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity documents of the specified category
     */
    Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocumentsByCategory(Long categoryId, PaginationRequest paginationRequest);

    /**
     * List identity documents by country with pagination.
     *
     * @param countryId the ID of the country
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity documents for the specified country
     */
    Mono<PaginationResponse<IdentityDocumentCatalogDTO>> listIdentityDocumentsByCountry(Long countryId, PaginationRequest paginationRequest);

    /**
     * Create a new identity document.
     *
     * @param identityDocumentDTO the identity document to create
     * @return the created identity document
     */
    Mono<IdentityDocumentCatalogDTO> createIdentityDocument(IdentityDocumentCatalogDTO identityDocumentDTO);

    /**
     * Get an identity document by its ID.
     *
     * @param documentId the ID of the identity document
     * @return the identity document with the specified ID
     */
    Mono<IdentityDocumentCatalogDTO> getIdentityDocument(Long documentId);

    /**
     * Get an identity document by its code.
     *
     * @param documentCode the code of the identity document
     * @return the identity document with the specified code
     */
    Mono<IdentityDocumentCatalogDTO> getIdentityDocumentByCode(String documentCode);

    /**
     * Update an existing identity document.
     *
     * @param documentId the ID of the identity document to update
     * @param identityDocumentDTO the updated identity document data
     * @return the updated identity document
     */
    Mono<IdentityDocumentCatalogDTO> updateIdentityDocument(Long documentId, IdentityDocumentCatalogDTO identityDocumentDTO);

    /**
     * Delete an identity document.
     *
     * @param documentId the ID of the identity document to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteIdentityDocument(Long documentId);
}
