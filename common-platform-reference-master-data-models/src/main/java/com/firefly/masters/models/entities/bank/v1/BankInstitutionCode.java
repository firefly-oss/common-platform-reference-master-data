package com.firefly.masters.models.entities.bank.v1;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("bank_institution_codes")
public class BankInstitutionCode {

    @Id
    @Column("institution_id")
    private Long institutionId;

    @Column("bank_name")
    private String bankName;

    @Column("swift_code")
    private String swiftCode;

    @Column("routing_number")
    private String routingNumber;

    @Column("iban_prefix")
    private String ibanPrefix;

    @Column("country_id")
    private Long countryId;  // References countries(country_id)

    @Column("institution_type_lkp_id")
    private Long institutionTypeLkpId;

    @Column("status")
    private StatusEnum status;

    @Column("svg_icon")
    private String svgIcon;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
