package com.catalis.masters.interfaces.dtos.holiday.v1;

import com.catalis.core.utils.annotations.FilterableId;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HolidayDTO {

    private Long holidayId;

    @FilterableId
    private Long countryId;

    @FilterableId
    private Long divisionId;

    private String holidayName;
    private String localName;
    private LocalDate holidayDate;
    private String recurrenceRule;
    private Long holidayTypeLkpId;
    private Boolean businessClosed;
    private Boolean bankClosed;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
