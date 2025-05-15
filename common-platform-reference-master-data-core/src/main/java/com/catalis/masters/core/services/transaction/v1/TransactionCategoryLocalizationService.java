package com.catalis.masters.core.services.transaction.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.transaction.v1.TransactionCategoryLocalizationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing transaction category localization operations.
 */
public interface TransactionCategoryLocalizationService {

    /**
     * List all localizations for a specific transaction category.
     *
     * @param categoryId the ID of the transaction category
     * @return a Flux of transaction category localizations
     */
    Flux<TransactionCategoryLocalizationDTO> getLocalizationsByCategoryId(Long categoryId);

    /**
     * List all localizations for a specific transaction category with pagination.
     *
     * @param categoryId the ID of the transaction category
     * @param paginationRequest pagination parameters
     * @return a paginated list of transaction category localizations
     */
    Mono<PaginationResponse<TransactionCategoryLocalizationDTO>> listLocalizationsByCategoryId(Long categoryId, PaginationRequest paginationRequest);

    /**
     * Create a new transaction category localization.
     *
     * @param localizationDTO the transaction category localization to create
     * @return the created transaction category localization
     */
    Mono<TransactionCategoryLocalizationDTO> createTransactionCategoryLocalization(TransactionCategoryLocalizationDTO localizationDTO);

    /**
     * Get a transaction category localization by its ID.
     *
     * @param localizationId the ID of the transaction category localization
     * @return the transaction category localization with the specified ID
     */
    Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalization(Long localizationId);

    /**
     * Get a transaction category localization by category ID and locale ID.
     *
     * @param categoryId the ID of the transaction category
     * @param localeId the ID of the locale
     * @return the transaction category localization for the specified category and locale
     */
    Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalizationByCategoryAndLocale(Long categoryId, Long localeId);

    /**
     * Update a transaction category localization.
     *
     * @param localizationId the ID of the transaction category localization to update
     * @param localizationDTO the updated transaction category localization data
     * @return the updated transaction category localization
     */
    Mono<TransactionCategoryLocalizationDTO> updateTransactionCategoryLocalization(Long localizationId, TransactionCategoryLocalizationDTO localizationDTO);

    /**
     * Delete a transaction category localization.
     *
     * @param localizationId the ID of the transaction category localization to delete
     * @return a Mono that completes when the transaction category localization is deleted
     */
    Mono<Void> deleteTransactionCategoryLocalization(Long localizationId);
}
