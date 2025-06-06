package com.catalis.masters.interfaces.dtos.contracttype.v1;

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
public class ContractTypeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long contractId;

    private String contractCode;
    private String description;
    private String name;
    private Boolean isActive;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}