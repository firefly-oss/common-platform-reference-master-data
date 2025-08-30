package com.firefly.masters.interfaces.dtos.currency.v1;

import com.firefly.annotations.ValidCurrencyCode;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long currencyId;

    @ValidCurrencyCode
    @NotBlank(message = "ISO code is required")
    private String isoCode;

    @NotBlank(message = "Currency name is required")
    @Size(max = 100, message = "Currency name must not exceed 100 characters")
    private String currencyName;

    @Size(max = 10, message = "Currency symbol must not exceed 10 characters")
    private String symbol;

    @Min(value = 0, message = "Decimal precision must be non-negative")
    @Max(value = 10, message = "Decimal precision must not exceed 10")
    private Integer decimalPrecision;

    private Boolean isMajor;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
