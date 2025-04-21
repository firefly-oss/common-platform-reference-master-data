package com.catalis.masters.interfaces.dtos.legal.v1;

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
public class LegalFormDTO {
    private Long legalFormId;

    @FilterableId
    private Long countryId;

    private String code;
    private String name;
    private String description;
    private Boolean isCorporate;
    private String entityType;
    private StatusEnum status;
}
