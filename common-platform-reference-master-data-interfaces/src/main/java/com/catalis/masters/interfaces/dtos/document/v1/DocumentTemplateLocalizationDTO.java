package com.catalis.masters.interfaces.dtos.document.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long localizationId;

    private Long templateId;
    private Long localeId;
    private String templateName;
    private String templateContent;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
