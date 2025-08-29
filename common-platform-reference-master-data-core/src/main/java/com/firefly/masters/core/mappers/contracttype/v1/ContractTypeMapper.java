package com.firefly.masters.core.mappers.contracttype.v1;

import com.firefly.masters.interfaces.dtos.contracttype.v1.ContractTypeDTO;
import com.firefly.masters.models.entities.contracttype.v1.ContractType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractTypeMapper {
    ContractTypeDTO toDTO(ContractType entity);
    ContractType toEntity(ContractTypeDTO dto);
}