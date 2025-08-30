package com.firefly.masters.interfaces.dtos.ruleoperationtype.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RuleOperationTypeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long operationTypeId;

    @NotBlank(message = "Operation type code is required")
    @Size(max = 50, message = "Operation type code must not exceed 50 characters")
    private String operationTypeCode;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
