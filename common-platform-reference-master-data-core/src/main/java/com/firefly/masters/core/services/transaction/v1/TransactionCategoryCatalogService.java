package com.firefly.masters.core.services.transaction.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.transaction.v1.TransactionCategoryCatalogDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing transaction category catalog operations.
 */
public interface TransactionCategoryCatalogService {

    /**
     * List all transaction categories with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated list of transaction categories
     */
    Mono<PaginationResponse<TransactionCategoryCatalogDTO>> listTransactionCategories(PaginationRequest paginationRequest);

    /**
     * List all root transaction categories (categories without a parent) with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated list of root transaction categories
     */
    Mono<PaginationResponse<TransactionCategoryCatalogDTO>> listRootTransactionCategories(PaginationRequest paginationRequest);

    /**
     * List all child transaction categories for a specific parent category with pagination.
     *
     * @param parentCategoryId the ID of the parent category
     * @param paginationRequest pagination parameters
     * @return a paginated list of child transaction categories
     */
    Mono<PaginationResponse<TransactionCategoryCatalogDTO>> listChildTransactionCategories(Long parentCategoryId, PaginationRequest paginationRequest);

    /**
     * Create a new transaction category.
     *
     * @param transactionCategoryDTO the transaction category to create
     * @return the created transaction category
     */
    Mono<TransactionCategoryCatalogDTO> createTransactionCategory(TransactionCategoryCatalogDTO transactionCategoryDTO);

    /**
     * Get a transaction category by its ID.
     *
     * @param categoryId the ID of the transaction category
     * @return the transaction category with the specified ID
     */
    Mono<TransactionCategoryCatalogDTO> getTransactionCategory(Long categoryId);

    /**
     * Get a transaction category by its code.
     *
     * @param categoryCode the code of the transaction category
     * @return the transaction category with the specified code
     */
    Mono<TransactionCategoryCatalogDTO> getTransactionCategoryByCode(String categoryCode);

    /**
     * Update a transaction category.
     *
     * @param categoryId the ID of the transaction category to update
     * @param transactionCategoryDTO the updated transaction category data
     * @return the updated transaction category
     */
    Mono<TransactionCategoryCatalogDTO> updateTransactionCategory(Long categoryId, TransactionCategoryCatalogDTO transactionCategoryDTO);

    /**
     * Delete a transaction category.
     *
     * @param categoryId the ID of the transaction category to delete
     * @return a Mono that completes when the transaction category is deleted
     */
    Mono<Void> deleteTransactionCategory(Long categoryId);
}
