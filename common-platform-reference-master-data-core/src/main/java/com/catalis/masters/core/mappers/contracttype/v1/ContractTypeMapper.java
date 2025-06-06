package com.catalis.masters.core.mappers.contracttype.v1;

import com.catalis.masters.interfaces.dtos.contracttype.v1.ContractTypeDTO;
import com.catalis.masters.models.entities.contracttype.v1.ContractType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractTypeMapper {
    ContractTypeDTO toDTO(ContractType entity);
    ContractType toEntity(ContractTypeDTO dto);
}