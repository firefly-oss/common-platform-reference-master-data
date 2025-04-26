package com.catalis.masters.core.mappers.identitydocument.v1;

import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentLocalizationDTO;
import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentLocalization;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between IdentityDocumentLocalization entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IdentityDocumentLocalizationMapper {

    /**
     * Convert an IdentityDocumentLocalization entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    IdentityDocumentLocalizationDTO toDTO(IdentityDocumentLocalization entity);

    /**
     * Convert an IdentityDocumentLocalizationDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    IdentityDocumentLocalization toEntity(IdentityDocumentLocalizationDTO dto);
}
