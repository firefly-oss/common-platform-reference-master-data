package com.catalis.masters.core.mappers.locale.v1;

import com.catalis.masters.interfaces.dtos.locale.v1.LanguageLocaleDTO;
import com.catalis.masters.models.entities.locale.v1.LanguageLocale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguageLocaleMapper {
    LanguageLocaleDTO toDTO(LanguageLocale entity);
    LanguageLocale toEntity(LanguageLocaleDTO dto);
}
