package com.firefly.masters.models.entities.document.v1;

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

/**
 * Entity representing a document template catalog record.
 * This defines the templates that can be used for document generation.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("document_template_catalog")
public class DocumentTemplateCatalog {

    @Id
    @Column("template_id")
    private Long templateId;

    @Column("template_code")
    private String templateCode;

    @Column("type_id")
    private Long typeId;

    @Column("category")
    private String category;

    @Column("description")
    private String description;

    @Column("template_name")
    private String templateName;

    @Column("template_content")
    private String templateContent;

    @Column("template_variables")
    private String templateVariables;  // Stored as JSONB in the database

    @Column("version")
    private String version;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
