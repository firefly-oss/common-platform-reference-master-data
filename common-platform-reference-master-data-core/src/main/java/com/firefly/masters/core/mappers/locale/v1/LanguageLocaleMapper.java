package com.firefly.masters.core.mappers.locale.v1;

import com.firefly.masters.interfaces.dtos.locale.v1.LanguageLocaleDTO;
import com.firefly.masters.models.entities.locale.v1.LanguageLocale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageLocaleMapper {
    LanguageLocaleDTO toDTO(LanguageLocale entity);
    LanguageLocale toEntity(LanguageLocaleDTO dto);
}
