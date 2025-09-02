package com.firefly.masters.interfaces.dtos.legal.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LegalFormDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID legalFormId;

    @FilterableId
    @NotNull(message = "Country ID is required")
    private UUID countryId;

    @NotBlank(message = "Legal form code is required")
    @Size(max = 20, message = "Legal form code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Legal form name is required")
    @Size(max = 100, message = "Legal form name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Boolean isCorporate;

    @Size(max = 50, message = "Entity type must not exceed 50 characters")
    private String entityType;

    @NotNull(message = "Status is required")
    private StatusEnum status;
}
