package com.firefly.masters.interfaces.dtos.contractdocumenttype.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDocumentTypeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long documentTypeId;

    @NotBlank(message = "Document code is required")
    @Size(max = 50, message = "Document code must not exceed 50 characters")
    private String documentCode;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
