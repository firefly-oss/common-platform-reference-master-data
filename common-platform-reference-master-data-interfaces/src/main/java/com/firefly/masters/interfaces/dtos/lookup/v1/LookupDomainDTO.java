package com.firefly.masters.interfaces.dtos.lookup.v1;

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
public class LookupDomainDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID domainId;

    @NotBlank(message = "Domain code is required")
    @Size(max = 50, message = "Domain code must not exceed 50 characters")
    private String domainCode;

    @NotBlank(message = "Domain name is required")
    @Size(max = 100, message = "Domain name must not exceed 100 characters")
    private String domainName;

    @Size(max = 500, message = "Domain description must not exceed 500 characters")
    private String domainDesc;

    private UUID parentDomainId;
    private Boolean multiselectAllowed;
    private Boolean hierarchyAllowed;
    private Boolean tenantOverridable;

    @Size(max = 2000, message = "Extra JSON must not exceed 2000 characters")
    private String extraJson;

    private UUID tenantId;

    @NotNull(message = "Status is required")
    private StatusEnum status;
}
