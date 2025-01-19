package com.catalis.masters.models.entities.country.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.interfaces.enums.country.v1.RegionEnum;
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
@Table("countries")
public class Country {

    @Id
    @Column("id")
    private Long id;

    @Column("iso_code")
    private String isoCode;

    @Column("country_name")
    private String countryName;

    @Column("region")
    private RegionEnum region;

    @Column("status")
    private StatusEnum status;

    @Column("svg_flag")
    private String svgFlag;
}
