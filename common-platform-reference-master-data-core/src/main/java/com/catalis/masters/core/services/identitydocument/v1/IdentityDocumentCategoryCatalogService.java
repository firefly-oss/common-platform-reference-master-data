package com.catalis.masters.core.services.identitydocument.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCategoryCatalogDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing identity document category catalog operations.
 */
public interface IdentityDocumentCategoryCatalogService {

    /**
     * List all identity document categories with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity document categories
     */
    Mono<PaginationResponse<IdentityDocumentCategoryCatalogDTO>> listIdentityDocumentCategories(PaginationRequest paginationRequest);

    /**
     * Create a new identity document category.
     *
     * @param identityDocumentCategoryDTO the identity document category to create
     * @return the created identity document category
     */
    Mono<IdentityDocumentCategoryCatalogDTO> createIdentityDocumentCategory(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryDTO);

    /**
     * Get an identity document category by its ID.
     *
     * @param categoryId the ID of the identity document category
     * @return the identity document category with the specified ID
     */
    Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategory(Long categoryId);

    /**
     * Get an identity document category by its code.
     *
     * @param categoryCode the code of the identity document category
     * @return the identity document category with the specified code
     */
    Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategoryByCode(String categoryCode);

    /**
     * Update an existing identity document category.
     *
     * @param categoryId the ID of the identity document category to update
     * @param identityDocumentCategoryDTO the updated identity document category data
     * @return the updated identity document category
     */
    Mono<IdentityDocumentCategoryCatalogDTO> updateIdentityDocumentCategory(Long categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryDTO);

    /**
     * Delete an identity document category.
     *
     * @param categoryId the ID of the identity document category to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteIdentityDocumentCategory(Long categoryId);
}
