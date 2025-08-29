package com.firefly.masters.interfaces.dtos.lookup.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.core.utils.annotations.FilterableId;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookupItemDTO {
    private Long itemId;

    @FilterableId
    private Long domainId;

    private String itemCode;
    private String itemLabelDefault;
    private String itemDesc;
    private Long parentItemId;
    private Integer sortOrder;

    @ValidDate
    private LocalDate effectiveFrom;

    @ValidDate
    private LocalDate effectiveTo;

    private Boolean isCurrent;
    private String extraJson;
    private Long tenantId;
    private StatusEnum status;
}
