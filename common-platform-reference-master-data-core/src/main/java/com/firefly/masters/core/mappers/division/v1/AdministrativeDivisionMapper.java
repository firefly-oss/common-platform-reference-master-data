package com.firefly.masters.core.mappers.division.v1;

import com.firefly.masters.interfaces.dtos.division.v1.AdministrativeDivisionDTO;
import com.firefly.masters.models.entities.division.v1.AdministrativeDivision;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdministrativeDivisionMapper {
    AdministrativeDivisionDTO toDTO(AdministrativeDivision entity);
    AdministrativeDivision toEntity(AdministrativeDivisionDTO dto);
}
