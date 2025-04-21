package com.catalis.masters.interfaces.dtos.activity.v1;

import com.catalis.core.utils.annotations.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCodeDTO {
    private Long activityCodeId;

    @FilterableId
    private Long countryId;

    private String code;
    private String classificationSys;
    private String description;
    private Long parentCodeId;
    private Boolean highRisk;
    private String riskFactors;
    private StatusEnum status;
}
