package com.catalis.masters.interfaces.dtos.identitydocument.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long documentId;

    private String documentCode;
    private String documentName;
    private Long categoryId;
    private IdentityDocumentCategoryCatalogDTO category;
    private Long countryId;
    private String description;
    private String validationRegex;
    private String formatDescription;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
