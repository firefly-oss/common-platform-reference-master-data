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

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("branches")
public class Branch {

    @Id
    @Column("branch_id")
    private Long branchId;

    @Column("branch_code")
    private String branchCode;

    @Column("branch_name")
    private String branchName;

    @Column("address")
    private String address;

    @Column("postal_code")
    private String postalCode;

    @Column("city")
    private String city;

    @Column("division_id")
    private Long divisionId;

    @Column("country_id")
    private Long countryId;

    @Column("phone_number")
    private String phoneNumber;

    @Column("email")
    private String email;

    @Column("branch_type_lkp_id")
    private Long branchTypeLkpId;

    @Column("branch_manager_id")
    private Long branchManagerId;

    @Column("opening_hours")
    private String openingHours;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
