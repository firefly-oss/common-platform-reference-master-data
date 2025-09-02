package com.firefly.masters.interfaces.dtos.country.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.interfaces.enums.country.v1.RegionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID countryId;

    @NotBlank(message = "ISO code is required")
    @Pattern(regexp = "^[A-Z]{2,3}$", message = "ISO code must be 2 or 3 uppercase letters")
    private String isoCode;

    @NotBlank(message = "Country name is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String countryName;

    @NotNull(message = "Region is required")
    private RegionEnum region;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    @Size(max = 10000, message = "SVG flag data must not exceed 10000 characters")
    private String svgFlag;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
