package com.catalis.masters.core.mappers.branch.v1;

import com.catalis.masters.interfaces.dtos.branch.v1.BranchDTO;
import com.catalis.masters.models.entities.branch.v1.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    BranchDTO toDTO(Branch entity);
    Branch toEntity(BranchDTO dto);
}
