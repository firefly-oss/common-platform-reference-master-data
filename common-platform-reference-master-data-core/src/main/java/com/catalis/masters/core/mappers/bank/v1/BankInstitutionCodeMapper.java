package com.catalis.masters.core.mappers.bank.v1;

import com.catalis.masters.interfaces.dtos.bank.v1.BankInstitutionCodeDTO;
import com.catalis.masters.models.entities.bank.v1.BankInstitutionCode;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankInstitutionCodeMapper {
    BankInstitutionCodeDTO toDTO(BankInstitutionCode entity);
    BankInstitutionCode toEntity(BankInstitutionCodeDTO dto);
}