package com.catalis.masters.interfaces.dtos.currency.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

    private Long id;
    private String isoCode;
    private String currencyName;
    private String symbol;
    private String decimalPrecision;
    private StatusEnum status;
}