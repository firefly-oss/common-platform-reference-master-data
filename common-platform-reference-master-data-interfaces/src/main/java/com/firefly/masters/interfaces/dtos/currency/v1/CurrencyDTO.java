package com.firefly.masters.interfaces.dtos.currency.v1;

import com.firefly.annotations.ValidCurrencyCode;
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
public class CurrencyDTO {

    private Long currencyId;

    @ValidCurrencyCode
    private String isoCode;

    private String currencyName;
    private String symbol;
    private Integer decimalPrecision;
    private Boolean isMajor;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
