package com.catalis.masters.interfaces.dtos.country.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
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
    private Long countryId;
    private String isoCode;
    private String countryName;
    private Long regionLkpId;
    private StatusEnum status;
    private String svgFlag;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
