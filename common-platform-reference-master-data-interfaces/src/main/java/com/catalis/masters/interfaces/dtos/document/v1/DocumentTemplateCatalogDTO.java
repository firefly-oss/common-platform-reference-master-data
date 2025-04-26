package com.catalis.masters.interfaces.dtos.document.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object for document template catalog information.
 * Used for transferring document template catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long templateId;

    private String templateCode;
    private Long typeId;
    private DocumentTemplateTypeCatalogDTO templateType;
    private String category;
    private String description;
    private String templateName;
    private String templateContent;
    private Map<String, Object> templateVariables;
    private String version;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
