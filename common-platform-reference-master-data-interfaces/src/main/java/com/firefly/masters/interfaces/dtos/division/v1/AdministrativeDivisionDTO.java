package com.firefly.masters.interfaces.dtos.division.v1;

import com.firefly.core.utils.annotations.FilterableId;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdministrativeDivisionDTO {
    private Long divisionId;

    @FilterableId
    private Long countryId;

    private String code;
    private String name;
    private String level;
    private Long parentDivisionId;
    private StatusEnum status;
    private String postalCodePattern;
    private String timeZone;
}
