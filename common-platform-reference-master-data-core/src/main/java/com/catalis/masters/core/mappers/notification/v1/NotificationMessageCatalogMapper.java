package com.catalis.masters.core.mappers.notification.v1;

import com.catalis.masters.interfaces.dtos.notification.v1.MessageTypeCatalogDTO;
import com.catalis.masters.interfaces.dtos.notification.v1.NotificationMessageCatalogDTO;
import com.catalis.masters.models.entities.notification.v1.MessageTypeCatalog;
import com.catalis.masters.models.entities.notification.v1.NotificationMessageCatalog;
import com.catalis.masters.models.repositories.notification.v1.MessageTypeCatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Mapper for converting between NotificationMessageCatalog entities and DTOs.
 */
@Mapper(componentModel = "spring")
public abstract class NotificationMessageCatalogMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageTypeCatalogRepository messageTypeCatalogRepository;

    @Autowired
    private MessageTypeCatalogMapper messageTypeCatalogMapper;

    /**
     * Convert a NotificationMessageCatalog entity to a DTO.
     *
     * @param entity the entity to convert
     * @return the converted DTO
     */
    @Mapping(source = "parameters", target = "parameters", qualifiedByName = "parametersToMap")
    @Mapping(source = "typeId", target = "messageType", qualifiedByName = "typeIdToMessageType")
    public abstract NotificationMessageCatalogDTO toDTO(NotificationMessageCatalog entity);

    /**
     * Convert a NotificationMessageCatalogDTO to an entity.
     *
     * @param dto the DTO to convert
     * @return the converted entity
     */
    @Mapping(source = "parameters", target = "parameters", qualifiedByName = "mapToParameters")
    @Mapping(source = "messageType", target = "typeId", qualifiedByName = "messageTypeToTypeId")
    public abstract NotificationMessageCatalog toEntity(NotificationMessageCatalogDTO dto);

    /**
     * Convert a JSON string to a Map.
     *
     * @param parameters the JSON string to convert
     * @return the converted Map
     */
    @Named("parametersToMap")
    @SuppressWarnings("unchecked")
    protected Map<String, Object> parametersToMap(String parameters) {
        if (parameters == null || parameters.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(parameters, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting parameters JSON to Map", e);
        }
    }

    /**
     * Convert a Map to a JSON string.
     *
     * @param parameters the Map to convert
     * @return the converted JSON string
     */
    @Named("mapToParameters")
    protected String mapToParameters(Map<String, Object> parameters) {
        if (parameters == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(parameters);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting parameters Map to JSON", e);
        }
    }

    /**
     * Convert a type ID to a MessageTypeCatalogDTO.
     *
     * @param typeId the type ID to convert
     * @return the converted MessageTypeCatalogDTO
     */
    @Named("typeIdToMessageType")
    protected MessageTypeCatalogDTO typeIdToMessageType(Long typeId) {
        if (typeId == null) {
            return null;
        }
        return messageTypeCatalogRepository.findById(typeId)
                .map(messageTypeCatalogMapper::toDTO)
                .block();
    }

    /**
     * Convert a MessageTypeCatalogDTO to a type ID.
     *
     * @param messageType the MessageTypeCatalogDTO to convert
     * @return the converted type ID
     */
    @Named("messageTypeToTypeId")
    protected Long messageTypeToTypeId(MessageTypeCatalogDTO messageType) {
        if (messageType == null) {
            return null;
        }
        return messageType.getTypeId();
    }
}
