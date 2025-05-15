package com.catalis.masters.core.mappers.transaction.v1;

import com.catalis.masters.interfaces.dtos.transaction.v1.TransactionCategoryCatalogDTO;
import com.catalis.masters.models.entities.transaction.v1.TransactionCategoryCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting between TransactionCategoryCatalog entity and DTO.
 */
@Mapper(componentModel = "spring")
public interface TransactionCategoryCatalogMapper {
    
    /**
     * Convert entity to DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    @Mapping(target = "parentCategory", ignore = true)
    TransactionCategoryCatalogDTO toDTO(TransactionCategoryCatalog entity);
    
    /**
     * Convert DTO to entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    @Mapping(target = "parentCategoryId", source = "parentCategoryId")
    TransactionCategoryCatalog toEntity(TransactionCategoryCatalogDTO dto);
}
