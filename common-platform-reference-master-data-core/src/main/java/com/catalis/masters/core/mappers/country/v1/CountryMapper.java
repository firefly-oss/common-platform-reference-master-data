package com.catalis.masters.core.mappers.country.v1;

import com.catalis.masters.interfaces.dtos.country.v1.CountryDTO;
import com.catalis.masters.models.entities.country.v1.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDTO toDTO(Country entity);
    Country toEntity(CountryDTO dto);
}
