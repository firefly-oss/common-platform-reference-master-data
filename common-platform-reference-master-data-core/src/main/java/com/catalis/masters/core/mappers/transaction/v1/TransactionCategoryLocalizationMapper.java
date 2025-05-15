package com.catalis.masters.core.mappers.transaction.v1;

import com.catalis.masters.interfaces.dtos.transaction.v1.TransactionCategoryLocalizationDTO;
import com.catalis.masters.models.entities.transaction.v1.TransactionCategoryLocalization;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between TransactionCategoryLocalization entity and DTO.
 */
@Mapper(componentModel = "spring")
public interface TransactionCategoryLocalizationMapper {
    
    /**
     * Convert entity to DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    TransactionCategoryLocalizationDTO toDTO(TransactionCategoryLocalization entity);
    
    /**
     * Convert DTO to entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    TransactionCategoryLocalization toEntity(TransactionCategoryLocalizationDTO dto);
}
