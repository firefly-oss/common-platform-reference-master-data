package com.catalis.masters.interfaces.dtos.branch.v1;

import com.catalis.core.utils.annotations.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {

    private Long id;
    private String branchCode;
    private String branchName;
    private String address;
    private String city;
    private String province;

    @FilterableId
    private Long countryId;

    private String phoneNumber;
    private StatusEnum status;
}
