package com.firefly.masters.core.mappers.notification.v1;

import com.firefly.masters.interfaces.dtos.notification.v1.MessageTypeCatalogDTO;
import com.firefly.masters.models.entities.notification.v1.MessageTypeCatalog;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between MessageTypeCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface MessageTypeCatalogMapper {

    /**
     * Convert a MessageTypeCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    MessageTypeCatalogDTO toDTO(MessageTypeCatalog entity);

    /**
     * Convert a MessageTypeCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    MessageTypeCatalog toEntity(MessageTypeCatalogDTO dto);
}
