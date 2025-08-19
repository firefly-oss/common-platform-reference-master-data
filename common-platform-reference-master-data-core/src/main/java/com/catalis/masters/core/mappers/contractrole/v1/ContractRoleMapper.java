package com.catalis.masters.core.mappers.contractrole.v1;

import com.catalis.masters.interfaces.dtos.contractrole.v1.ContractRoleDTO;
import com.catalis.masters.models.entities.contractrole.v1.ContractRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractRoleMapper {
    ContractRoleDTO toDTO(ContractRole entity);
    ContractRole toEntity(ContractRoleDTO dto);
}
