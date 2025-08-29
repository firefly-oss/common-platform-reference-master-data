package com.firefly.masters.models.entities.division.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
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
@Table("administrative_division")
public class AdministrativeDivision {

    @Id
    @Column("division_id")
    private Long divisionId;

    @Column("country_id")
    private Long countryId;  // References countries(country_id)

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("level")
    private String level;

    @Column("parent_division_id")
    private Long parentDivisionId;  // Self-referencing to administrative_division(division_id)

    @Column("status")
    private StatusEnum status;

    @Column("postal_code_pattern")
    private String postalCodePattern;

    @Column("time_zone")
    private String timeZone;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}