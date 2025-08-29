package com.firefly.masters.core.mappers.title.v1;

import com.firefly.masters.interfaces.dtos.title.v1.TitleMasterDTO;
import com.firefly.masters.models.entities.title.v1.TitleMaster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitleMasterMapper {
    TitleMasterDTO toDTO(TitleMaster entity);
    TitleMaster toEntity(TitleMasterDTO dto);
}
