package com.catalis.masters.models.repositories.document.v1;

import com.catalis.masters.models.entities.document.v1.DocumentTemplateLocalization;
import com.catalis.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing DocumentTemplateLocalization entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface DocumentTemplateLocalizationRepository extends BaseRepository<DocumentTemplateLocalization, Long> {

    /**
     * Find all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Flux of DocumentTemplateLocalization entities for the specified template
     */
    Flux<DocumentTemplateLocalization> findByTemplateId(Long templateId);

    /**
     * Count localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM document_template_localization WHERE template_id = :templateId")
    Mono<Long> countByTemplateId(Long templateId);

    /**
     * Find all localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Flux of DocumentTemplateLocalization entities for the specified locale
     */
    Flux<DocumentTemplateLocalization> findByLocaleId(Long localeId);

    /**
     * Count localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM document_template_localization WHERE locale_id = :localeId")
    Mono<Long> countByLocaleId(Long localeId);

    /**
     * Find a specific localization by template ID and locale ID.
     *
     * @param templateId the ID of the document template
     * @param localeId the ID of the language locale
     * @return a Mono of DocumentTemplateLocalization
     */
    Mono<DocumentTemplateLocalization> findByTemplateIdAndLocaleId(Long templateId, Long localeId);

    /**
     * Delete all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Mono of Void
     */
    Mono<Void> deleteByTemplateId(Long templateId);
}
