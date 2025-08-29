package com.firefly.masters.core.mappers.lookup.v1;

import com.firefly.masters.interfaces.dtos.lookup.v1.LookupDomainDTO;
import com.firefly.masters.models.entities.lookup.v1.LookupDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LookupDomainMapper {
    LookupDomainDTO toDTO(LookupDomain entity);
    LookupDomain toEntity(LookupDomainDTO dto);
}
