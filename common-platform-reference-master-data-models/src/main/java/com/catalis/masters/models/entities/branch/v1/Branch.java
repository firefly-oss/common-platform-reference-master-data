package com.catalis.masters.models.entities.branch.v1;

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
@Table("branches")
public class Branch {

    @Id
    @Column("id")
    private Long id;

    @Column("branch_code")
    private String branchCode;

    @Column("branch_name")
    private String branchName;

    @Column("address")
    private String address;

    @Column("city")
    private String city;

    @Column("province")
    private String province;

    @Column("country_id")
    private Long countryId;

    @Column("phone_number")
    private String phoneNumber;

    @Column("status")
    private StatusEnum status;
}