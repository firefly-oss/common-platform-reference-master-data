package com.firefly.masters.core.mappers.identitydocument.v1;

import com.firefly.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCategoryCatalogDTO;
import com.firefly.masters.models.entities.identitydocument.v1.IdentityDocumentCategoryCatalog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for converting between IdentityDocumentCategoryCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IdentityDocumentCategoryCatalogMapper {

    /**
     * Convert an IdentityDocumentCategoryCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    IdentityDocumentCategoryCatalogDTO toDTO(IdentityDocumentCategoryCatalog entity);

    /**
     * Convert an IdentityDocumentCategoryCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    IdentityDocumentCategoryCatalog toEntity(IdentityDocumentCategoryCatalogDTO dto);
}
