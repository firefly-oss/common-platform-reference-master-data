package com.firefly.masters.core.mappers.lookup.v1;

import com.firefly.masters.interfaces.dtos.lookup.v1.LookupItemDTO;
import com.firefly.masters.models.entities.lookup.v1.LookupItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LookupItemMapper {
    LookupItemDTO toDTO(LookupItem entity);
    LookupItem toEntity(LookupItemDTO dto);
}
