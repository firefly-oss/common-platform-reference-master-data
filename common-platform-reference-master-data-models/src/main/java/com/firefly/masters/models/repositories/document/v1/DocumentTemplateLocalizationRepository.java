package com.firefly.masters.models.repositories.document.v1;

import com.firefly.masters.models.entities.document.v1.DocumentTemplateLocalization;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Repository for managing DocumentTemplateLocalization entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface DocumentTemplateLocalizationRepository extends BaseRepository<DocumentTemplateLocalization, UUID> {

    /**
     * Find all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Flux of DocumentTemplateLocalization entities for the specified template
     */
    Flux<DocumentTemplateLocalization> findByTemplateId(UUID templateId);

    /**
     * Count localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM document_template_localization WHERE template_id = :templateId")
    Mono<Long> countByTemplateId(UUID templateId);

    /**
     * Find all localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Flux of DocumentTemplateLocalization entities for the specified locale
     */
    Flux<DocumentTemplateLocalization> findByLocaleId(UUID localeId);

    /**
     * Count localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM document_template_localization WHERE locale_id = :localeId")
    Mono<Long> countByLocaleId(UUID localeId);

    /**
     * Find a specific localization by template ID and locale ID.
     *
     * @param templateId the ID of the document template
     * @param localeId the ID of the language locale
     * @return a Mono of DocumentTemplateLocalization
     */
    Mono<DocumentTemplateLocalization> findByTemplateIdAndLocaleId(UUID templateId, UUID localeId);

    /**
     * Delete all localizations for a specific template.
     *
     * @param templateId the ID of the document template
     * @return a Mono of Void
     */
    Mono<Void> deleteByTemplateId(UUID templateId);
}
