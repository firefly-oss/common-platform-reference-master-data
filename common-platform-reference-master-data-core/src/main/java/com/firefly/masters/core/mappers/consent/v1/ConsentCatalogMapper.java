package com.firefly.masters.core.mappers.consent.v1;

import com.firefly.masters.interfaces.dtos.consent.v1.ConsentCatalogDTO;
import com.firefly.masters.models.entities.consent.v1.ConsentCatalog;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between ConsentCatalog entities and DTOs.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = "spring")
public interface ConsentCatalogMapper {
    /**
     * Converts a ConsentCatalog entity to a ConsentCatalogDTO.
     *
     * @param entity the ConsentCatalog entity to convert
     * @return the corresponding ConsentCatalogDTO
     */
    ConsentCatalogDTO toDTO(ConsentCatalog entity);
    
    /**
     * Converts a ConsentCatalogDTO to a ConsentCatalog entity.
     *
     * @param dto the ConsentCatalogDTO to convert
     * @return the corresponding ConsentCatalog entity
     */
    ConsentCatalog toEntity(ConsentCatalogDTO dto);
}