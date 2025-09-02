package com.firefly.masters.interfaces.dtos.contracttype.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ContractTypeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID contractId;

    @NotBlank(message = "Contract code is required")
    @Size(max = 20, message = "Contract code must not exceed 20 characters")
    private String contractCode;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Contract name is required")
    @Size(max = 100, message = "Contract name must not exceed 100 characters")
    private String name;

    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}