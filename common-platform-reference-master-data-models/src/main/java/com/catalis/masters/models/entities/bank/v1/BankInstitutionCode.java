package com.catalis.masters.models.entities.bank.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("bank_institution_codes")
public class BankInstitutionCode {

    @Id
    @Column("id")
    private Long id;

    @Column("bank_name")
    private String bankName;

    @Column("swift_code")
    private String swiftCode;

    @Column("routing_number")
    private String routingNumber;

    @Column("country_id")
    private Long countryId;  // References countries(id)

    @Column("status")
    private StatusEnum status;

    @Column("svg_icon")
    private String svgIcon;
}