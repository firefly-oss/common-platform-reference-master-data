package com.catalis.masters.models.repositories.identitydocument.v1;

import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentLocalization;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing IdentityDocumentLocalization entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface IdentityDocumentLocalizationRepository extends BaseRepository<IdentityDocumentLocalization, Long> {
    
    /**
     * Find all localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @param pageable pagination information
     * @return a Flux of IdentityDocumentLocalization entities for the specified document
     */
    Flux<IdentityDocumentLocalization> findByDocumentId(Long documentId, Pageable pageable);
    
    /**
     * Count localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM identity_document_localization WHERE document_id = :documentId")
    Mono<Long> countByDocumentId(Long documentId);
    
    /**
     * Find a localization for a specific identity document and locale.
     *
     * @param documentId the ID of the identity document
     * @param localeId the ID of the locale
     * @return a Mono of IdentityDocumentLocalization
     */
    Mono<IdentityDocumentLocalization> findByDocumentIdAndLocaleId(Long documentId, Long localeId);
    
    /**
     * Delete all localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @return a Mono of Void
     */
    Mono<Void> deleteByDocumentId(Long documentId);
}
