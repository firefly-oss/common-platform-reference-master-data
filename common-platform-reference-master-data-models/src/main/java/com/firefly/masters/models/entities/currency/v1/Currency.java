package com.firefly.masters.models.entities.currency.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("currencies")
public class Currency {

    @Id
    @Column("currency_id")
    private UUID currencyId;

    @Column("iso_code")
    private String isoCode;

    @Column("currency_name")
    private String currencyName;

    @Column("symbol")
    private String symbol;

    @Column("decimal_precision")
    private Integer decimalPrecision;

    @Column("is_major")
    private Boolean isMajor;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
