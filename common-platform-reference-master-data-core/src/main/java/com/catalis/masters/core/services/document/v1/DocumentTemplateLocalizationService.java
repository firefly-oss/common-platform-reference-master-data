package com.catalis.masters.core.services.document.v1;

import com.catalis.masters.interfaces.dtos.document.v1.DocumentTemplateLocalizationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing document template localization operations.
 */
public interface DocumentTemplateLocalizationService {

    /**
     * Get all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Flux of document template localization DTOs
     */
    Flux<DocumentTemplateLocalizationDTO> getLocalizationsByTemplateId(Long templateId);

    /**
     * Get all localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Flux of document template localization DTOs
     */
    Flux<DocumentTemplateLocalizationDTO> getLocalizationsByLocaleId(Long localeId);

    /**
     * Create a new document template localization.
     *
     * @param localizationDTO the document template localization data
     * @return the created document template localization DTO
     */
    Mono<DocumentTemplateLocalizationDTO> createDocumentTemplateLocalization(DocumentTemplateLocalizationDTO localizationDTO);

    /**
     * Get a document template localization by ID.
     *
     * @param localizationId the ID of the document template localization
     * @return the document template localization DTO
     */
    Mono<DocumentTemplateLocalizationDTO> getDocumentTemplateLocalization(Long localizationId);

    /**
     * Get a document template localization by template ID and locale ID.
     *
     * @param templateId the ID of the document template
     * @param localeId the ID of the language locale
     * @return the document template localization DTO
     */
    Mono<DocumentTemplateLocalizationDTO> getDocumentTemplateLocalizationByTemplateAndLocale(Long templateId, Long localeId);

    /**
     * Update a document template localization.
     *
     * @param localizationId the ID of the document template localization to update
     * @param localizationDTO the updated document template localization data
     * @return the updated document template localization DTO
     */
    Mono<DocumentTemplateLocalizationDTO> updateDocumentTemplateLocalization(Long localizationId, DocumentTemplateLocalizationDTO localizationDTO);

    /**
     * Delete a document template localization.
     *
     * @param localizationId the ID of the document template localization to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteDocumentTemplateLocalization(Long localizationId);

    /**
     * Delete all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Mono of Void
     */
    Mono<Void> deleteLocalizationsByTemplateId(Long templateId);
}
