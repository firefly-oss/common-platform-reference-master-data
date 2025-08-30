package com.firefly.masters.interfaces.dtos.division.v1;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministrativeDivisionDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long divisionId;

    @FilterableId
    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotBlank(message = "Division code is required")
    @Size(max = 20, message = "Division code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Division name is required")
    @Size(max = 100, message = "Division name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Division level is required")
    @Size(max = 50, message = "Division level must not exceed 50 characters")
    private String level;

    private Long parentDivisionId;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    @Size(max = 50, message = "Postal code pattern must not exceed 50 characters")
    private String postalCodePattern;

    @Size(max = 50, message = "Time zone must not exceed 50 characters")
    private String timeZone;
}
