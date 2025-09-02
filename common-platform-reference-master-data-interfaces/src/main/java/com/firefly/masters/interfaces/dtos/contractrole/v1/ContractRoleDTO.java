package com.firefly.masters.interfaces.dtos.contractrole.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ContractRoleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID roleId;

    private String roleCode;
    private String description;
    private String name;
    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
