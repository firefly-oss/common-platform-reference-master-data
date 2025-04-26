package com.catalis.masters.models.entities.document.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entity representing a localized version of a document template.
 * This stores translations of document templates for different languages/locales.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("document_template_localization")
public class DocumentTemplateLocalization {

    @Id
    @Column("localization_id")
    private Long localizationId;

    @Column("template_id")
    private Long templateId;  // References document_template_catalog(template_id)

    @Column("locale_id")
    private Long localeId;  // References language_locale(locale_id)

    @Column("template_name")
    private String templateName;

    @Column("template_content")
    private String templateContent;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
