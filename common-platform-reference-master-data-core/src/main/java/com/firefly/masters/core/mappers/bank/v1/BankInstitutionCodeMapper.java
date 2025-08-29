package com.firefly.masters.core.mappers.bank.v1;

import com.firefly.masters.interfaces.dtos.bank.v1.BankInstitutionCodeDTO;
import com.firefly.masters.models.entities.bank.v1.BankInstitutionCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BankInstitutionCodeMapper {
    BankInstitutionCodeDTO toDTO(BankInstitutionCode entity);
    BankInstitutionCode toEntity(BankInstitutionCodeDTO dto);
}
