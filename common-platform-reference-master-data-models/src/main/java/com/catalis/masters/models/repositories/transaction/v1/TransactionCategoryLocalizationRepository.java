package com.catalis.masters.models.repositories.transaction.v1;

import com.catalis.masters.models.entities.transaction.v1.TransactionCategoryLocalization;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing TransactionCategoryLocalization entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface TransactionCategoryLocalizationRepository extends BaseRepository<TransactionCategoryLocalization, Long> {

    /**
     * Find all localizations for a specific transaction category.
     *
     * @param categoryId the ID of the transaction category
     * @return a Flux of TransactionCategoryLocalization entities for the specified category
     */
    Flux<TransactionCategoryLocalization> findByCategoryId(Long categoryId);

    /**
     * Find all localizations for a specific transaction category with pagination.
     *
     * @param categoryId the ID of the transaction category
     * @param pageable pagination information
     * @return a Flux of TransactionCategoryLocalization entities for the specified category
     */
    Flux<TransactionCategoryLocalization> findByCategoryId(Long categoryId, Pageable pageable);

    /**
     * Count all localizations for a specific transaction category.
     *
     * @param categoryId the ID of the transaction category
     * @return a Mono with the count of localizations for the specified category
     */
    Mono<Long> countByCategoryId(Long categoryId);

    /**
     * Find a localization for a specific transaction category and locale.
     *
     * @param categoryId the ID of the transaction category
     * @param localeId the ID of the locale
     * @return a Mono of TransactionCategoryLocalization for the specified category and locale
     */
    Mono<TransactionCategoryLocalization> findByCategoryIdAndLocaleId(Long categoryId, Long localeId);

    /**
     * Delete all localizations for a specific transaction category.
     *
     * @param categoryId the ID of the transaction category
     * @return a Mono that completes when the localizations are deleted
     */
    Mono<Void> deleteByCategoryId(Long categoryId);
}
