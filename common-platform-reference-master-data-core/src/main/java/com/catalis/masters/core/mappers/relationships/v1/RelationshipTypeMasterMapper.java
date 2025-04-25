package com.catalis.masters.core.mappers.relationships.v1;


import com.catalis.masters.interfaces.dtos.relationships.v1.RelationshipTypeMasterDTO;
import com.catalis.masters.models.entities.relationships.v1.RelationshipTypeMaster;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelationshipTypeMasterMapper {
    RelationshipTypeMasterDTO toDTO(RelationshipTypeMaster entity);
    RelationshipTypeMaster toEntity(RelationshipTypeMasterDTO dto);
}