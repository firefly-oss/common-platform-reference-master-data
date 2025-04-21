package com.catalis.masters.core.services.holiday.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.holiday.v1.HolidayMapper;
import com.catalis.masters.interfaces.dtos.holiday.v1.HolidayDTO;
import com.catalis.masters.models.entities.holiday.v1.Holiday;
import com.catalis.masters.models.repositories.holiday.v1.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository repository;

    @Autowired
    private HolidayMapper mapper;

    @Override
    public Mono<PaginationResponse<HolidayDTO>> listHolidays(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<HolidayDTO> createHoliday(HolidayDTO holidayDto) {
        Holiday holiday = mapper.toEntity(holidayDto);
        return repository.save(holiday)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<HolidayDTO> getHoliday(Long holidayId) {
        return repository.findById(holidayId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<HolidayDTO> updateHoliday(Long holidayId, HolidayDTO holidayDto) {
        return repository.findById(holidayId)
                .flatMap(existingHoliday -> {
                    Holiday updatedHoliday = mapper.toEntity(holidayDto);
                    updatedHoliday.setHolidayId(existingHoliday.getHolidayId());
                    return repository.save(updatedHoliday);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteHoliday(Long holidayId) {
        return repository.deleteById(holidayId);
    }
}
