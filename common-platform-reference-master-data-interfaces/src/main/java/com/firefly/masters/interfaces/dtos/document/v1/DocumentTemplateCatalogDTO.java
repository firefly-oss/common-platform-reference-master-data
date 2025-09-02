package com.firefly.masters.interfaces.dtos.document.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Data Transfer Object for document template catalog information.
 * Used for transferring document template catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID templateId;

    @NotBlank(message = "Template code is required")
    @Size(max = 50, message = "Template code must not exceed 50 characters")
    private String templateCode;

    @NotNull(message = "Type ID is required")
    private UUID typeId;

    @Valid
    private DocumentTemplateTypeCatalogDTO templateType;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Template name is required")
    @Size(max = 100, message = "Template name must not exceed 100 characters")
    private String templateName;

    @NotBlank(message = "Template content is required")
    @Size(max = 10000, message = "Template content must not exceed 10000 characters")
    private String templateContent;

    private Map<String, Object> templateVariables;

    @Size(max = 20, message = "Version must not exceed 20 characters")
    private String version;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
