package com.firefly.masters.interfaces.dtos.lookup.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookupDomainDTO {
    private Long domainId;
    private String domainCode;
    private String domainName;
    private String domainDesc;
    private Long parentDomainId;
    private Boolean multiselectAllowed;
    private Boolean hierarchyAllowed;
    private Boolean tenantOverridable;
    private String extraJson;
    private Long tenantId;
    private StatusEnum status;
}
