package com.catalis.masters.interfaces.dtos.holiday.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.interfaces.enums.holidays.v1.HolidayTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDTO {

    private Long id;

    @FilterableId
    private Long countryId;

    private String holidayName;
    private String holidayDate;
    private HolidayTypeEnum holidayType;
    private StatusEnum status;
}