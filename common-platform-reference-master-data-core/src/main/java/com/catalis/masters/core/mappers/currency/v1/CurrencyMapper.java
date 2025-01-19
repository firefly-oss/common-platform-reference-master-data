package com.catalis.masters.core.mappers.currency.v1;

import com.catalis.masters.interfaces.dtos.currency.v1.CurrencyDTO;
import com.catalis.masters.models.entities.currency.v1.Currency;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDTO toDTO(Currency entity);
    Currency toEntity(CurrencyDTO dto);
}
