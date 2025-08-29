package com.firefly.masters.core.mappers.legal.v1;

import com.firefly.masters.interfaces.dtos.legal.v1.LegalFormDTO;
import com.firefly.masters.models.entities.legal.v1.LegalForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LegalFormMapper {
    LegalFormDTO toDTO(LegalForm entity);
    LegalForm toEntity(LegalFormDTO dto);
}
