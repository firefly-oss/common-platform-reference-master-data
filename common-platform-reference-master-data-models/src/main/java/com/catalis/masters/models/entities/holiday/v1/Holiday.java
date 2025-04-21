package com.catalis.masters.models.entities.holiday.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("holidays")
public class Holiday {

    @Id
    @Column("holiday_id")
    private Long holidayId;

    @Column("country_id")
    private Long countryId;  // References countries(country_id)

    @Column("division_id")
    private Long divisionId;  // References administrative_division(division_id)

    @Column("holiday_name")
    private String holidayName;

    @Column("local_name")
    private String localName;

    @Column("holiday_date")
    private LocalDate holidayDate;

    @Column("recurrence_rule")
    private String recurrenceRule;

    @Column("holiday_type_lkp_id")
    private Long holidayTypeLkpId;

    @Column("business_closed")
    private Boolean businessClosed;

    @Column("bank_closed")
    private Boolean bankClosed;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
