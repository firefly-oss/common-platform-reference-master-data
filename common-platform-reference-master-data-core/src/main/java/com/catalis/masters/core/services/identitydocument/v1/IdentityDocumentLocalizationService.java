package com.catalis.masters.core.services.identitydocument.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentLocalizationDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing identity document localization operations.
 */
public interface IdentityDocumentLocalizationService {

    /**
     * List all identity document localizations with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity document localizations
     */
    Mono<PaginationResponse<IdentityDocumentLocalizationDTO>> listIdentityDocumentLocalizations(PaginationRequest paginationRequest);

    /**
     * Get all localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @param paginationRequest pagination parameters
     * @return a paginated list of identity document localization DTOs
     */
    Mono<PaginationResponse<IdentityDocumentLocalizationDTO>> getLocalizationsByDocumentId(Long documentId, PaginationRequest paginationRequest);

    /**
     * Create a new identity document localization.
     *
     * @param localizationDTO the identity document localization to create
     * @return the created identity document localization DTO
     */
    Mono<IdentityDocumentLocalizationDTO> createIdentityDocumentLocalization(IdentityDocumentLocalizationDTO localizationDTO);

    /**
     * Get an identity document localization by document ID and locale ID.
     *
     * @param documentId the ID of the identity document
     * @param localeId the ID of the language locale
     * @return the identity document localization DTO
     */
    Mono<IdentityDocumentLocalizationDTO> getIdentityDocumentLocalizationByDocumentAndLocale(Long documentId, Long localeId);

    /**
     * Update an identity document localization.
     *
     * @param localizationId the ID of the identity document localization to update
     * @param localizationDTO the updated identity document localization data
     * @return the updated identity document localization DTO
     */
    Mono<IdentityDocumentLocalizationDTO> updateIdentityDocumentLocalization(Long localizationId, IdentityDocumentLocalizationDTO localizationDTO);

    /**
     * Delete an identity document localization.
     *
     * @param localizationId the ID of the identity document localization to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteIdentityDocumentLocalization(Long localizationId);

    /**
     * Delete all localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @return a Mono of Void
     */
    Mono<Void> deleteLocalizationsByDocumentId(Long documentId);
}
