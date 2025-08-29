package com.firefly.masters.interfaces.dtos.bank.v1;

import com.firefly.annotations.ValidBic;
import com.firefly.core.utils.annotations.FilterableId;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankInstitutionCodeDTO {

    private Long institutionId;
    private String bankName;

    @ValidBic
    private String swiftCode;

    private String routingNumber;
    private String ibanPrefix;

    @FilterableId
    private Long countryId;

    private Long institutionTypeLkpId;
    private StatusEnum status;
    private String svgIcon;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
