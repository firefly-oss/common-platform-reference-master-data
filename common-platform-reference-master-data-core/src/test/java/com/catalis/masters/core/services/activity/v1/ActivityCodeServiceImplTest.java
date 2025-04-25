package com.catalis.masters.core.services.activity.v1;

import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.mappers.activity.v1.ActivityCodeMapper;
import com.catalis.masters.core.utils.TestPaginationRequest;
import com.catalis.masters.interfaces.dtos.activity.v1.ActivityCodeDTO;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.activity.v1.ActivityCode;
import com.catalis.masters.models.repositories.activity.v1.ActivityCodeRepository;
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

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityCodeServiceImplTest {

    @Mock
    private ActivityCodeRepository repository;

    @Mock
    private ActivityCodeMapper mapper;

    @InjectMocks
    private ActivityCodeServiceImpl service;

    private ActivityCode entity;
    private ActivityCodeDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        entity = new ActivityCode();
        entity.setActivityCodeId(1L);
        entity.setCountryId(1L);
        entity.setCode("A01");
        entity.setClassificationSys("NAICS");
        entity.setDescription("Crop Production");
        entity.setParentCodeId(null);
        entity.setHighRisk(false);
        entity.setRiskFactors(null);
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateUpdated(LocalDateTime.now());

        dto = new ActivityCodeDTO();
        dto.setActivityCodeId(1L);
        dto.setCountryId(1L);
        dto.setCode("A01");
        dto.setClassificationSys("NAICS");
        dto.setDescription("Crop Production");
        dto.setParentCodeId(null);
        dto.setHighRisk(false);
        dto.setRiskFactors(null);
        dto.setStatus(StatusEnum.ACTIVE);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listActivityCodes_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<ActivityCodeDTO>> result = service.listActivityCodes(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getActivityCodeId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void getActivityCodesByCountry_ShouldReturnActivityCodes() {
        // Arrange
        when(repository.findByCountryId(anyLong())).thenReturn(Flux.just(entity));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Flux<ActivityCodeDTO> result = service.getActivityCodesByCountry(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByCountryId(anyLong());
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void getChildActivityCodes_ShouldReturnChildActivityCodes() {
        // Arrange
        when(repository.findByParentCodeId(anyLong())).thenReturn(Flux.just(entity));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Flux<ActivityCodeDTO> result = service.getChildActivityCodes(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByParentCodeId(anyLong());
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void createActivityCode_ShouldReturnCreatedActivityCode() {
        // Arrange
        when(mapper.toEntity(any(ActivityCodeDTO.class))).thenReturn(entity);
        when(repository.save(any(ActivityCode.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Mono<ActivityCodeDTO> result = service.createActivityCode(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(ActivityCodeDTO.class));
        verify(repository).save(any(ActivityCode.class));
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void getActivityCode_ShouldReturnActivityCodeWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Mono<ActivityCodeDTO> result = service.getActivityCode(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void getActivityCode_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<ActivityCodeDTO> result = service.getActivityCode(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(ActivityCode.class));
    }

    @Test
    void updateActivityCode_ShouldReturnUpdatedActivityCodeWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(ActivityCodeDTO.class))).thenReturn(entity);
        when(repository.save(any(ActivityCode.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ActivityCode.class))).thenReturn(dto);

        // Act
        Mono<ActivityCodeDTO> result = service.updateActivityCode(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(ActivityCodeDTO.class));
        verify(repository).save(any(ActivityCode.class));
        verify(mapper).toDTO(any(ActivityCode.class));
    }

    @Test
    void updateActivityCode_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<ActivityCodeDTO> result = service.updateActivityCode(1L, dto);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(ActivityCodeDTO.class));
        verify(repository, never()).save(any(ActivityCode.class));
        verify(mapper, never()).toDTO(any(ActivityCode.class));
    }

    @Test
    void deleteActivityCode_ShouldDeleteWhenFound() {
        // Arrange
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteActivityCode(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).deleteById(anyLong());
    }
}
