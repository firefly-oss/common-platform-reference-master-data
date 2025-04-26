package com.catalis.masters.interfaces.dtos.document.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for document template type catalog information.
 * Used for transferring document template type catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTemplateTypeCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long typeId;

    private String typeCode;
    private String typeName;
    private String description;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
