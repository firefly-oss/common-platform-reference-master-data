package com.firefly.masters.core.services.identitydocument.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentLocalizationDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

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
    Mono<PaginationResponse<IdentityDocumentLocalizationDTO>> getLocalizationsByDocumentId(UUID documentId, PaginationRequest paginationRequest);

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
    Mono<IdentityDocumentLocalizationDTO> getIdentityDocumentLocalizationByDocumentAndLocale(UUID documentId, UUID localeId);

    /**
     * Update an identity document localization.
     *
     * @param localizationId the ID of the identity document localization to update
     * @param localizationDTO the updated identity document localization data
     * @return the updated identity document localization DTO
     */
    Mono<IdentityDocumentLocalizationDTO> updateIdentityDocumentLocalization(UUID localizationId, IdentityDocumentLocalizationDTO localizationDTO);

    /**
     * Delete an identity document localization.
     *
     * @param localizationId the ID of the identity document localization to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteIdentityDocumentLocalization(UUID localizationId);

    /**
     * Delete all localizations for a specific identity document.
     *
     * @param documentId the ID of the identity document
     * @return a Mono of Void
     */
    Mono<Void> deleteLocalizationsByDocumentId(UUID documentId);
}
