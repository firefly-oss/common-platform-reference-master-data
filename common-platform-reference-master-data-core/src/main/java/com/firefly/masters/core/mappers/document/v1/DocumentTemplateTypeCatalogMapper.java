package com.firefly.masters.core.mappers.document.v1;

import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateTypeCatalogDTO;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateTypeCatalog;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between DocumentTemplateTypeCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface DocumentTemplateTypeCatalogMapper {

    /**
     * Convert a DocumentTemplateTypeCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    DocumentTemplateTypeCatalogDTO toDTO(DocumentTemplateTypeCatalog entity);

    /**
     * Convert a DocumentTemplateTypeCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    DocumentTemplateTypeCatalog toEntity(DocumentTemplateTypeCatalogDTO dto);
}
