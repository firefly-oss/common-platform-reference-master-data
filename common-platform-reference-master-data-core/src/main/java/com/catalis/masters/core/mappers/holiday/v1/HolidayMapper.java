package com.catalis.masters.core.mappers.holiday.v1;

import com.catalis.masters.interfaces.dtos.holiday.v1.HolidayDTO;
import com.catalis.masters.models.entities.holiday.v1.Holiday;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    HolidayDTO toDTO(Holiday entity);
    Holiday toEntity(HolidayDTO dto);
}
