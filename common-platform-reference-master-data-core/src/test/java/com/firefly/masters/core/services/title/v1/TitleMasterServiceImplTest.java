package com.firefly.masters.core.services.title.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.title.v1.TitleMasterMapper;
import com.firefly.masters.interfaces.dtos.title.v1.TitleMasterDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.title.v1.TitleMaster;
import com.firefly.masters.models.repositories.title.v1.TitleMasterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TitleMasterServiceImplTest {

    @Mock
    private TitleMasterRepository repository;

    @Mock
    private TitleMasterMapper mapper;

    @InjectMocks
    private TitleMasterServiceImpl service;

    private TitleMaster entity;
    private TitleMasterDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        entity = new TitleMaster();
        entity.setTitleId(1L);
        entity.setPrefix("MR");
        entity.setDescription("Mr.");
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(LocalDateTime.now());
        entity.setDateUpdated(LocalDateTime.now());

        dto = new TitleMasterDTO();
        dto.setTitleId(1L);
        dto.setPrefix("MR");
        dto.setDescription("Mr.");
        dto.setIsActive(true);
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(LocalDateTime.now());
        dto.setDateUpdated(LocalDateTime.now());

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listTitles_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(TitleMaster.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<TitleMasterDTO>> result = service.listTitles(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getTitleId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(TitleMaster.class));
    }

    @Test
    void createTitle_ShouldReturnCreatedEntity() {
        // Arrange
        when(mapper.toEntity(any(TitleMasterDTO.class))).thenReturn(entity);
        when(repository.save(any(TitleMaster.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(TitleMaster.class))).thenReturn(dto);

        // Act
        Mono<TitleMasterDTO> result = service.createTitle(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(TitleMasterDTO.class));
        verify(repository).save(any(TitleMaster.class));
        verify(mapper).toDTO(any(TitleMaster.class));
    }

    @Test
    void getTitle_ShouldReturnEntityWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(TitleMaster.class))).thenReturn(dto);

        // Act
        Mono<TitleMasterDTO> result = service.getTitle(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(TitleMaster.class));
    }

    @Test
    void getTitle_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<TitleMasterDTO> result = service.getTitle(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(TitleMaster.class));
    }

    @Test
    void updateTitle_ShouldReturnUpdatedEntityWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(TitleMasterDTO.class))).thenReturn(entity);
        when(repository.save(any(TitleMaster.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(TitleMaster.class))).thenReturn(dto);

        // Act
        Mono<TitleMasterDTO> result = service.updateTitle(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(TitleMasterDTO.class));
        verify(repository).save(any(TitleMaster.class));
        verify(mapper).toDTO(any(TitleMaster.class));
    }

    @Test
    void updateTitle_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<TitleMasterDTO> result = service.updateTitle(1L, dto);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(TitleMasterDTO.class));
        verify(repository, never()).save(any(TitleMaster.class));
        verify(mapper, never()).toDTO(any(TitleMaster.class));
    }

    @Test
    void deleteTitle_ShouldDeleteWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(repository.delete(any(TitleMaster.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteTitle(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository).delete(any(TitleMaster.class));
    }

    @Test
    void deleteTitle_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteTitle(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository, never()).delete(any(TitleMaster.class));
    }
}
