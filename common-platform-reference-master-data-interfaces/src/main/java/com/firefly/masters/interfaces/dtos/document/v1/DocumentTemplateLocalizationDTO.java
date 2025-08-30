package com.firefly.masters.interfaces.dtos.document.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for document template localization information.
 * Used for transferring document template localization data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateLocalizationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long localizationId;

    @NotNull(message = "Template ID is required")
    private Long templateId;

    @NotNull(message = "Locale ID is required")
    private Long localeId;

    @NotBlank(message = "Template name is required")
    @Size(max = 100, message = "Template name must not exceed 100 characters")
    private String templateName;

    @NotBlank(message = "Template content is required")
    @Size(max = 10000, message = "Template content must not exceed 10000 characters")
    private String templateContent;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
