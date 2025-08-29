package com.firefly.masters.core.mappers.document.v1;

import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateLocalizationDTO;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateLocalization;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between DocumentTemplateLocalization entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface DocumentTemplateLocalizationMapper {

    /**
     * Convert a DocumentTemplateLocalization entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    DocumentTemplateLocalizationDTO toDTO(DocumentTemplateLocalization entity);

    /**
     * Convert a DocumentTemplateLocalizationDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    DocumentTemplateLocalization toEntity(DocumentTemplateLocalizationDTO dto);
}
