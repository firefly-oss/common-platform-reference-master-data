package com.catalis.masters.interfaces.dtos.bank.v1;

import com.catalis.core.utils.annotations.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankInstitutionCodeDTO {

    private Long id;
    private String bankName;
    private String swiftCode;
    private String routingNumber;

    @FilterableId
    private Long countryId;

    private StatusEnum status;
    private String svgIcon;
}
