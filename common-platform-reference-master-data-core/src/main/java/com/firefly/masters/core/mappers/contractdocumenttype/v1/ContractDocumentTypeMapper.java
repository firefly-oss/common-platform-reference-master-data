package com.firefly.masters.core.mappers.contractdocumenttype.v1;

import com.firefly.masters.interfaces.dtos.contractdocumenttype.v1.ContractDocumentTypeDTO;
import com.firefly.masters.models.entities.contractdocumenttype.v1.ContractDocumentType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractDocumentTypeMapper {
    ContractDocumentTypeDTO toDTO(ContractDocumentType entity);
    ContractDocumentType toEntity(ContractDocumentTypeDTO dto);
}
