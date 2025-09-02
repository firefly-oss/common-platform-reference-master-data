package com.firefly.masters.models.entities.identitydocument.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an identity document catalog record.
 * This defines the identity documents that can be used for identification.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("identity_document_catalog")
public class IdentityDocumentCatalog {

    @Id
    @Column("document_id")
    private UUID documentId;

    @Column("document_code")
    private String documentCode;

    @Column("document_name")
    private String documentName;

    @Column("category_id")
    private UUID categoryId;

    @Column("country_id")
    private UUID countryId;

    @Column("description")
    private String description;

    @Column("validation_regex")
    private String validationRegex;

    @Column("format_description")
    private String formatDescription;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
