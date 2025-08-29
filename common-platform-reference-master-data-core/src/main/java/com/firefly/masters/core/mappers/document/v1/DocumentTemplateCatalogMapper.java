package com.firefly.masters.core.mappers.document.v1;

import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateCatalogDTO;
import com.firefly.masters.interfaces.dtos.document.v1.DocumentTemplateTypeCatalogDTO;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateCatalog;
import com.firefly.masters.models.entities.document.v1.DocumentTemplateTypeCatalog;
import com.firefly.masters.models.repositories.document.v1.DocumentTemplateTypeCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Mapper for converting between DocumentTemplateCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring")
public abstract class DocumentTemplateCatalogMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DocumentTemplateTypeCatalogRepository documentTemplateTypeCatalogRepository;

    @Autowired
    private DocumentTemplateTypeCatalogMapper documentTemplateTypeCatalogMapper;

    /**
     * Convert a DocumentTemplateCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    @Mapping(source = "templateVariables", target = "templateVariables", qualifiedByName = "templateVariablesToMap")
    @Mapping(source = "typeId", target = "templateType", qualifiedByName = "typeIdToTemplateType")
    public abstract DocumentTemplateCatalogDTO toDTO(DocumentTemplateCatalog entity);

    /**
     * Convert a DocumentTemplateCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    @Mapping(source = "templateVariables", target = "templateVariables", qualifiedByName = "mapToTemplateVariables")
    @Mapping(source = "templateType", target = "typeId", qualifiedByName = "templateTypeToTypeId")
    public abstract DocumentTemplateCatalog toEntity(DocumentTemplateCatalogDTO dto);

    /**
     * Convert a JSON string to a Map.
     *
     * @param templateVariables the JSON string to convert
     * @return the converted Map
     */
    @Named("templateVariablesToMap")
    protected Map<String, Object> templateVariablesToMap(String templateVariables) {
        if (templateVariables == null) {
            return null;
        }
        try {
            return objectMapper.readValue(templateVariables, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting template variables JSON to Map", e);
        }
    }

    /**
     * Convert a Map to a JSON string.
     *
     * @param templateVariables the Map to convert
     * @return the converted JSON string
     */
    @Named("mapToTemplateVariables")
    protected String mapToTemplateVariables(Map<String, Object> templateVariables) {
        if (templateVariables == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(templateVariables);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting template variables Map to JSON", e);
        }
    }

    /**
     * Convert a type ID to a DocumentTemplateTypeCatalogDTO.
     *
     * @param typeId the type ID to convert
     * @return the converted DocumentTemplateTypeCatalogDTO
     */
    @Named("typeIdToTemplateType")
    protected DocumentTemplateTypeCatalogDTO typeIdToTemplateType(Long typeId) {
        if (typeId == null) {
            return null;
        }
        return documentTemplateTypeCatalogRepository.findById(typeId)
                .map(documentTemplateTypeCatalogMapper::toDTO)
                .block();
    }

    /**
     * Convert a DocumentTemplateTypeCatalogDTO to a type ID.
     *
     * @param templateType the DocumentTemplateTypeCatalogDTO to convert
     * @return the converted type ID
     */
    @Named("templateTypeToTypeId")
    protected Long templateTypeToTypeId(DocumentTemplateTypeCatalogDTO templateType) {
        if (templateType == null) {
            return null;
        }
        return templateType.getTypeId();
    }
}
