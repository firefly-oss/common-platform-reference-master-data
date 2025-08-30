package com.firefly.masters.interfaces.dtos.identitydocument.v1;

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
 * Data Transfer Object for identity document localization information.
 * Used for transferring identity document localization data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDocumentLocalizationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long localizationId;

    @NotNull(message = "Document ID is required")
    private Long documentId;

    @NotNull(message = "Locale ID is required")
    private Long localeId;

    @NotBlank(message = "Document name is required")
    @Size(max = 100, message = "Document name must not exceed 100 characters")
    private String documentName;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 200, message = "Format description must not exceed 200 characters")
    private String formatDescription;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
