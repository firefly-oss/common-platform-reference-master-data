package com.firefly.masters.interfaces.dtos.country.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.interfaces.enums.country.v1.RegionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long countryId;
    
    private String isoCode;
    private String countryName;
    private RegionEnum region;
    private StatusEnum status;
    private String svgFlag;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
