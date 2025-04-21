package com.catalis.masters.core.mappers.activity.v1;

import com.catalis.masters.interfaces.dtos.activity.v1.ActivityCodeDTO;
import com.catalis.masters.models.entities.activity.v1.ActivityCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActivityCodeMapper {
    ActivityCodeDTO toDTO(ActivityCode entity);
    ActivityCode toEntity(ActivityCodeDTO dto);
}
