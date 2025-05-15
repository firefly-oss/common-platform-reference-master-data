package com.catalis.masters.models.repositories.transaction.v1;

import com.catalis.masters.models.entities.transaction.v1.TransactionCategoryCatalog;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing TransactionCategoryCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface TransactionCategoryCatalogRepository extends BaseRepository<TransactionCategoryCatalog, Long> {

    /**
     * Find a transaction category by its code.
     *
     * @param categoryCode the unique code of the transaction category
     * @return a Mono of TransactionCategoryCatalog
     */
    Mono<TransactionCategoryCatalog> findByCategoryCode(String categoryCode);

    /**
     * Find all transaction categories with a specific parent category.
     *
     * @param parentCategoryId the ID of the parent category
     * @param pageable pagination information
     * @return a Flux of TransactionCategoryCatalog entities with the specified parent
     */
    Flux<TransactionCategoryCatalog> findByParentCategoryId(Long parentCategoryId, Pageable pageable);

    /**
     * Count all transaction categories with a specific parent category.
     *
     * @param parentCategoryId the ID of the parent category
     * @return a Mono with the count of categories with the specified parent
     */
    Mono<Long> countByParentCategoryId(Long parentCategoryId);

    /**
     * Find all root transaction categories (categories without a parent).
     *
     * @param pageable pagination information
     * @return a Flux of TransactionCategoryCatalog entities without a parent
     */
    @Query("SELECT * FROM transaction_category_catalog WHERE parent_category_id IS NULL")
    Flux<TransactionCategoryCatalog> findRootCategories(Pageable pageable);

    /**
     * Count all root transaction categories (categories without a parent).
     *
     * @return a Mono with the count of categories without a parent
     */
    @Query("SELECT COUNT(*) FROM transaction_category_catalog WHERE parent_category_id IS NULL")
    Mono<Long> countRootCategories();
}
