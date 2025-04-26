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
 * Entity representing an identity document category catalog record.
 * This defines the categories of identity documents.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("identity_document_category_catalog")
public class IdentityDocumentCategoryCatalog {

    @Id
    @Column("category_id")
    private Long categoryId;

    @Column("category_code")
    private String categoryCode;

    @Column("category_name")
    private String categoryName;

    @Column("description")
    private String description;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
