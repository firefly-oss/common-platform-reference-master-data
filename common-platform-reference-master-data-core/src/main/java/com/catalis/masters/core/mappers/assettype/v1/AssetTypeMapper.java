package com.catalis.masters.core.mappers.assettype.v1;

import com.catalis.masters.interfaces.dtos.assettype.v1.AssetTypeDTO;
import com.catalis.masters.models.entities.assettype.v1.AssetType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetTypeMapper {
    AssetTypeDTO toDTO(AssetType entity);
    AssetType toEntity(AssetTypeDTO dto);
}