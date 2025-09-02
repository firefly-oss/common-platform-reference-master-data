package com.firefly.masters.models.entities.country.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.interfaces.enums.country.v1.RegionEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("countries")
public class Country {

    @Id
    @Column("country_id")
    private UUID countryId;

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

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
