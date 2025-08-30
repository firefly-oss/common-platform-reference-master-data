package com.firefly.masters.interfaces.dtos.identitydocument.v1;

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


/**
 * Data Transfer Object for identity document catalog information.
 * Used for transferring identity document catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDocumentCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long documentId;

    @NotBlank(message = "Document code is required")
    @Size(max = 20, message = "Document code must not exceed 20 characters")
    private String documentCode;

    @NotBlank(message = "Document name is required")
    @Size(max = 100, message = "Document name must not exceed 100 characters")
    private String documentName;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @Valid
    private IdentityDocumentCategoryCatalogDTO category;

    @NotNull(message = "Country ID is required")
    private Long countryId;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Size(max = 200, message = "Validation regex must not exceed 200 characters")
    private String validationRegex;

    @Size(max = 200, message = "Format description must not exceed 200 characters")
    private String formatDescription;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
