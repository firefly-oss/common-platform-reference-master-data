package com.catalis.masters.core.mappers.identitydocument.v1;

import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCatalogDTO;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCategoryCatalogDTO;
import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentCatalog;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentCategoryCatalogRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for converting between IdentityDocumentCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {IdentityDocumentCategoryCatalogMapper.class})
public abstract class IdentityDocumentCatalogMapper {

    @Autowired
    private IdentityDocumentCategoryCatalogRepository identityDocumentCategoryCatalogRepository;

    @Autowired
    private IdentityDocumentCategoryCatalogMapper identityDocumentCategoryCatalogMapper;

    /**
     * Convert an IdentityDocumentCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "categoryIdToCategory")
    public abstract IdentityDocumentCatalogDTO toDTO(IdentityDocumentCatalog entity);

    /**
     * Convert an IdentityDocumentCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    public abstract IdentityDocumentCatalog toEntity(IdentityDocumentCatalogDTO dto);

    /**
     * Convert a category ID to an IdentityDocumentCategoryCatalogDTO.
     *
     * @param categoryId the category ID to convert
     * @return the converted IdentityDocumentCategoryCatalogDTO
     */
    @Named("categoryIdToCategory")
    protected IdentityDocumentCategoryCatalogDTO categoryIdToCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return identityDocumentCategoryCatalogRepository.findById(categoryId)
                .map(identityDocumentCategoryCatalogMapper::toDTO)
                .block();
    }
}
