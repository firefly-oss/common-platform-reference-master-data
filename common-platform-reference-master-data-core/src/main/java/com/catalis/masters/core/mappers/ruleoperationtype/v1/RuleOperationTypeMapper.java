package com.catalis.masters.core.mappers.ruleoperationtype.v1;

import com.catalis.masters.interfaces.dtos.ruleoperationtype.v1.RuleOperationTypeDTO;
import com.catalis.masters.models.entities.ruleoperationtype.v1.RuleOperationType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuleOperationTypeMapper {
    RuleOperationTypeDTO toDTO(RuleOperationType entity);
    RuleOperationType toEntity(RuleOperationTypeDTO dto);
}
