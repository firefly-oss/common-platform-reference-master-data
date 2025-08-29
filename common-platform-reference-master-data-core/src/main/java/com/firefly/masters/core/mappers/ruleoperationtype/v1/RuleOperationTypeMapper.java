package com.firefly.masters.core.mappers.ruleoperationtype.v1;

import com.firefly.masters.interfaces.dtos.ruleoperationtype.v1.RuleOperationTypeDTO;
import com.firefly.masters.models.entities.ruleoperationtype.v1.RuleOperationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuleOperationTypeMapper {
    RuleOperationTypeDTO toDTO(RuleOperationType entity);
    RuleOperationType toEntity(RuleOperationTypeDTO dto);
}
