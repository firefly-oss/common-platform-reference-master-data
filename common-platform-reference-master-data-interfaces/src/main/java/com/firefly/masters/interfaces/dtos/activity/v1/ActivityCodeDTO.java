package com.firefly.masters.interfaces.dtos.activity.v1;

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
public class ActivityCodeDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long activityCodeId;

    @FilterableId
    @NotNull(message = "Country ID is required")
    private Long countryId;

    @NotBlank(message = "Activity code is required")
    @Size(max = 20, message = "Activity code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Classification system is required")
    @Size(max = 50, message = "Classification system must not exceed 50 characters")
    private String classificationSys;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private Long parentCodeId;
    private Boolean highRisk;

    @Size(max = 1000, message = "Risk factors must not exceed 1000 characters")
    private String riskFactors;

    @NotNull(message = "Status is required")
    private StatusEnum status;
}
