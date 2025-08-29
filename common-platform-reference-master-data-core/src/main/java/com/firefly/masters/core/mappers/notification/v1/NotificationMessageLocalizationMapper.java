package com.firefly.masters.core.mappers.notification.v1;

import com.firefly.masters.interfaces.dtos.notification.v1.NotificationMessageLocalizationDTO;
import com.firefly.masters.models.entities.notification.v1.NotificationMessageLocalization;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between NotificationMessageLocalization entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface NotificationMessageLocalizationMapper {

    /**
     * Convert a NotificationMessageLocalization entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    NotificationMessageLocalizationDTO toDTO(NotificationMessageLocalization entity);

    /**
     * Convert a NotificationMessageLocalizationDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    NotificationMessageLocalization toEntity(NotificationMessageLocalizationDTO dto);
}
