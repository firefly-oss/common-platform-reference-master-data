package com.firefly.masters.core.services.division.v1;

import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.division.v1.AdministrativeDivisionMapper;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.masters.interfaces.dtos.division.v1.AdministrativeDivisionDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.division.v1.AdministrativeDivision;
import com.firefly.masters.models.repositories.division.v1.AdministrativeDivisionRepository;
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
public class AdministrativeDivisionServiceImplTest {

    @Mock
    private AdministrativeDivisionRepository repository;

    @Mock
    private AdministrativeDivisionMapper mapper;

    @InjectMocks
    private AdministrativeDivisionServiceImpl service;

    private AdministrativeDivision entity;
    private AdministrativeDivisionDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        entity = new AdministrativeDivision();
        entity.setDivisionId(1L);
        entity.setCountryId(1L);
        entity.setCode("NY");
        entity.setName("New York");
        entity.setLevel("STATE");
        entity.setParentDivisionId(null);
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setPostalCodePattern("\\d{5}(-\\d{4})?");
        entity.setTimeZone("America/New_York");
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateUpdated(LocalDateTime.now());

        dto = new AdministrativeDivisionDTO();
        dto.setDivisionId(1L);
        dto.setCountryId(1L);
        dto.setCode("NY");
        dto.setName("New York");
        dto.setLevel("STATE");
        dto.setParentDivisionId(null);
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setPostalCodePattern("\\d{5}(-\\d{4})?");
        dto.setTimeZone("America/New_York");

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listDivisions_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(AdministrativeDivision.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<AdministrativeDivisionDTO>> result = service.listDivisions(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getDivisionId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void createDivision_ShouldReturnCreatedDivision() {
        // Arrange
        when(mapper.toEntity(any(AdministrativeDivisionDTO.class))).thenReturn(entity);
        when(repository.save(any(AdministrativeDivision.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(AdministrativeDivision.class))).thenReturn(dto);

        // Act
        Mono<AdministrativeDivisionDTO> result = service.createDivision(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(AdministrativeDivisionDTO.class));
        verify(repository).save(any(AdministrativeDivision.class));
        verify(mapper).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void getDivision_ShouldReturnDivisionWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(AdministrativeDivision.class))).thenReturn(dto);

        // Act
        Mono<AdministrativeDivisionDTO> result = service.getDivision(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void getDivision_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<AdministrativeDivisionDTO> result = service.getDivision(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void updateDivision_ShouldReturnUpdatedDivisionWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(AdministrativeDivisionDTO.class))).thenReturn(entity);
        when(repository.save(any(AdministrativeDivision.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(AdministrativeDivision.class))).thenReturn(dto);

        // Act
        Mono<AdministrativeDivisionDTO> result = service.updateDivision(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(AdministrativeDivisionDTO.class));
        verify(repository).save(any(AdministrativeDivision.class));
        verify(mapper).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void updateDivision_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<AdministrativeDivisionDTO> result = service.updateDivision(1L, dto);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(AdministrativeDivisionDTO.class));
        verify(repository, never()).save(any(AdministrativeDivision.class));
        verify(mapper, never()).toDTO(any(AdministrativeDivision.class));
    }

    @Test
    void deleteDivision_ShouldDeleteWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(repository.delete(any(AdministrativeDivision.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteDivision(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository).delete(any(AdministrativeDivision.class));
    }

    @Test
    void deleteDivision_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteDivision(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository, never()).delete(any(AdministrativeDivision.class));
    }
}
