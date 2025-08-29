package com.firefly.masters.interfaces.dtos.ruleoperationtype.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class RuleOperationTypeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long operationTypeId;

    private String operationTypeCode;
    private String description;
    private String name;
    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
