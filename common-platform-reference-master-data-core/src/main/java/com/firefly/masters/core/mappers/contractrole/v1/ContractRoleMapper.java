package com.firefly.masters.core.mappers.contractrole.v1;

import com.firefly.masters.interfaces.dtos.contractrole.v1.ContractRoleDTO;
import com.firefly.masters.models.entities.contractrole.v1.ContractRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractRoleMapper {
    ContractRoleDTO toDTO(ContractRole entity);
    ContractRole toEntity(ContractRoleDTO dto);
}
