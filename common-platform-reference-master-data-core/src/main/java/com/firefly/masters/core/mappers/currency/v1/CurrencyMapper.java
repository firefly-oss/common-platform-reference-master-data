package com.firefly.masters.core.mappers.currency.v1;

import com.firefly.masters.interfaces.dtos.currency.v1.CurrencyDTO;
import com.firefly.masters.models.entities.currency.v1.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDTO toDTO(Currency entity);
    Currency toEntity(CurrencyDTO dto);
}
