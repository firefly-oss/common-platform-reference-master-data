package com.firefly.masters.models.repositories.identitydocument.v1;

import com.firefly.masters.models.entities.identitydocument.v1.IdentityDocumentCatalog;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing IdentityDocumentCatalog entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface IdentityDocumentCatalogRepository extends BaseRepository<IdentityDocumentCatalog, Long> {

    /**
     * Find an identity document by its code.
     *
     * @param documentCode the unique code of the identity document
     * @return a Mono of IdentityDocumentCatalog
     */
    Mono<IdentityDocumentCatalog> findByDocumentCode(String documentCode);

    /**
     * Find all identity documents of a specific category.
     *
     * @param categoryId the ID of the identity document category
     * @param pageable pagination information
     * @return a Flux of IdentityDocumentCatalog entities of the specified category
     */
    Flux<IdentityDocumentCatalog> findByCategoryId(Long categoryId, Pageable pageable);

    /**
     * Count identity documents of a specific category.
     *
     * @param categoryId the ID of the identity document category
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM identity_document_catalog WHERE category_id = :categoryId")
    Mono<Long> countByCategoryId(Long categoryId);

    /**
     * Find all identity documents for a specific country.
     *
     * @param countryId the ID of the country
     * @param pageable pagination information
     * @return a Flux of IdentityDocumentCatalog entities for the specified country
     */
    Flux<IdentityDocumentCatalog> findByCountryId(Long countryId, Pageable pageable);

    /**
     * Count identity documents for a specific country.
     *
     * @param countryId the ID of the country
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM identity_document_catalog WHERE country_id = :countryId")
    Mono<Long> countByCountryId(Long countryId);
}
