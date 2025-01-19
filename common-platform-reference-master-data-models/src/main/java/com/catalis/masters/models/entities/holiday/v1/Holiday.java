package com.catalis.masters.models.entities.holiday.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.interfaces.enums.holidays.v1.HolidayTypeEnum;
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
@Table("holidays")
public class Holiday {

    @Id
    @Column("id")
    private Long id;

    @Column("country_id")
    private Long countryId;  // References countries(id)

    @Column("holiday_name")
    private String holidayName;

    @Column("holiday_date")
    private String holidayDate;

    @Column("holiday_type")
    private HolidayTypeEnum holidayType;

    @Column("status")
    private StatusEnum status;
}
