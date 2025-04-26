package com.catalis.masters.models.entities.identitydocument.v1;

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
 * Entity representing a localized version of an identity document.
 * This stores translations of identity document information for different languages/locales.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("identity_document_localization")
public class IdentityDocumentLocalization {

    @Id
    @Column("localization_id")
    private Long localizationId;

    @Column("document_id")
    private Long documentId;  // References identity_document_catalog(document_id)

    @Column("locale_id")
    private Long localeId;  // References language_locale(locale_id)

    @Column("document_name")
    private String documentName;

    @Column("description")
    private String description;

    @Column("format_description")
    private String formatDescription;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
