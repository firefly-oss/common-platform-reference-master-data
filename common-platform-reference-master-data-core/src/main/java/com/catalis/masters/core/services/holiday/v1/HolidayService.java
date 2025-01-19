package com.catalis.masters.core.services.holiday.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.holiday.v1.HolidayDTO;
import reactor.core.publisher.Mono;

public interface HolidayService {

    /**
     * Retrieves a paginated list of holidays based on the provided pagination parameters.
     *
     * @param paginationRequest the request containing pagination details such as page number and page size
     * @return a reactive Mono emitting the paginated response containing the list of holidays
     */
    Mono<PaginationResponse<HolidayDTO>> listHolidays(PaginationRequest paginationRequest);

    /**
     * Creates a new holiday entry in the system.
     *
     * @param holidayDto the HolidayDTO object containing the details of the holiday to be created
     * @return a Mono emitting the created HolidayDTO upon successful creation
     */
    Mono<HolidayDTO> createHoliday(HolidayDTO holidayDto);

    /**
     * Retrieves the details of a specific holiday by its ID.
     *
     * @param holidayId The unique identifier of the holiday to be retrieved.
     * @return A {@code Mono<HolidayDTO>} containing the details of the holiday,
     *         or an empty Mono if no holiday is found with the provided ID.
     */
    Mono<HolidayDTO> getHoliday(Long holidayId);

    /**
     * Updates an existing holiday with the provided details.
     *
     * @param holidayId the unique identifier of the holiday to be updated
     * @param holidayDto the data transfer object containing the updated details of the holiday
     * @return a Mono emitting the updated HolidayDTO after the update is completed
     */
    Mono<HolidayDTO> updateHoliday(Long holidayId, HolidayDTO holidayDto);

    /**
     * Deletes a holiday identified by its unique ID.
     *
     * @param holidayId the unique ID of the holiday to be deleted
     * @return a {@code Mono<Void>} that completes when the holiday is deleted
     */
    Mono<Void> deleteHoliday(Long holidayId);
}
