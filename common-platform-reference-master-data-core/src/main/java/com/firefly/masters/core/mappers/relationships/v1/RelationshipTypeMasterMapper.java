package com.firefly.masters.core.mappers.relationships.v1;


import com.firefly.masters.interfaces.dtos.relationships.v1.RelationshipTypeMasterDTO;
import com.firefly.masters.models.entities.relationships.v1.RelationshipTypeMaster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelationshipTypeMasterMapper {
    RelationshipTypeMasterDTO toDTO(RelationshipTypeMaster entity);
    RelationshipTypeMaster toEntity(RelationshipTypeMasterDTO dto);
}