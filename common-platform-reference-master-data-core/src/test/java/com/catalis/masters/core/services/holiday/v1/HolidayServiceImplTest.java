package com.catalis.masters.core.services.holiday.v1;

import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.mappers.holiday.v1.HolidayMapper;
import com.catalis.masters.core.utils.TestPaginationRequest;
import com.catalis.masters.interfaces.dtos.holiday.v1.HolidayDTO;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.holiday.v1.Holiday;
import com.catalis.masters.models.repositories.holiday.v1.HolidayRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HolidayServiceImplTest {

    @Mock
    private HolidayRepository repository;

    @Mock
    private HolidayMapper mapper;

    @InjectMocks
    private HolidayServiceImpl service;

    private Holiday entity;
    private HolidayDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        LocalDate holidayDate = LocalDate.of(2023, 12, 25);

        entity = new Holiday();
        entity.setHolidayId(1L);
        entity.setCountryId(1L);
        entity.setDivisionId(null);
        entity.setHolidayName("Christmas Day");
        entity.setLocalName("Christmas Day");
        entity.setHolidayDate(holidayDate);
        entity.setRecurrenceRule("FREQ=YEARLY;BYMONTH=12;BYMONTHDAY=25");
        entity.setHolidayTypeLkpId(1L);
        entity.setBusinessClosed(true);
        entity.setBankClosed(true);
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(now);
        entity.setDateUpdated(now);

        dto = new HolidayDTO();
        dto.setHolidayId(1L);
        dto.setCountryId(1L);
        dto.setDivisionId(null);
        dto.setHolidayName("Christmas Day");
        dto.setLocalName("Christmas Day");
        dto.setHolidayDate(holidayDate);
        dto.setRecurrenceRule("FREQ=YEARLY;BYMONTH=12;BYMONTHDAY=25");
        dto.setHolidayTypeLkpId(1L);
        dto.setBusinessClosed(true);
        dto.setBankClosed(true);
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listHolidays_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(Holiday.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<HolidayDTO>> result = service.listHolidays(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getHolidayId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(Holiday.class));
    }

    @Test
    void createHoliday_ShouldReturnCreatedHoliday() {
        // Arrange
        when(mapper.toEntity(any(HolidayDTO.class))).thenReturn(entity);
        when(repository.save(any(Holiday.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(Holiday.class))).thenReturn(dto);

        // Act
        Mono<HolidayDTO> result = service.createHoliday(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(HolidayDTO.class));
        verify(repository).save(any(Holiday.class));
        verify(mapper).toDTO(any(Holiday.class));
    }

    @Test
    void getHoliday_ShouldReturnHolidayWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(Holiday.class))).thenReturn(dto);

        // Act
        Mono<HolidayDTO> result = service.getHoliday(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(Holiday.class));
    }

    @Test
    void getHoliday_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<HolidayDTO> result = service.getHoliday(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(Holiday.class));
    }

    @Test
    void updateHoliday_ShouldReturnUpdatedHolidayWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(HolidayDTO.class))).thenReturn(entity);
        when(repository.save(any(Holiday.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(Holiday.class))).thenReturn(dto);

        // Act
        Mono<HolidayDTO> result = service.updateHoliday(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(HolidayDTO.class));
        verify(repository).save(any(Holiday.class));
        verify(mapper).toDTO(any(Holiday.class));
    }

    @Test
    void updateHoliday_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<HolidayDTO> result = service.updateHoliday(1L, dto);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(HolidayDTO.class));
        verify(repository, never()).save(any(Holiday.class));
        verify(mapper, never()).toDTO(any(Holiday.class));
    }

    @Test
    void deleteHoliday_ShouldDeleteWhenFound() {
        // Arrange
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteHoliday(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteHoliday_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteHoliday(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).deleteById(anyLong());
    }
}
