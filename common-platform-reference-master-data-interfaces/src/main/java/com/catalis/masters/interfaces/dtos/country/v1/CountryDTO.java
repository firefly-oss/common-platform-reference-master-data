package com.catalis.masters.interfaces.dtos.country.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.interfaces.enums.country.v1.RegionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {
    private Long id;
    private String isoCode;
    private String countryName;
    private RegionEnum region;
    private StatusEnum status;
    private String svgFlag;
}
