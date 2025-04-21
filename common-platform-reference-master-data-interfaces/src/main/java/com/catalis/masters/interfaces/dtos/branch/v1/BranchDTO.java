package com.catalis.masters.interfaces.dtos.branch.v1;

import com.catalis.core.utils.annotations.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private Long branchId;
    private String branchCode;
    private String branchName;
    private String address;
    private String postalCode;
    private String city;

    @FilterableId
    private Long divisionId;

    @FilterableId
    private Long countryId;

    private String phoneNumber;
    private String email;
    private Long branchTypeLkpId;
    private Long branchManagerId;
    private String openingHours;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
