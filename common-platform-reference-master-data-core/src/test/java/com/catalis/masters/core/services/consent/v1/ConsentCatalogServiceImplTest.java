package com.catalis.masters.core.services.consent.v1;

import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.mappers.consent.v1.ConsentCatalogMapper;
import com.catalis.masters.core.utils.TestPaginationRequest;
import com.catalis.masters.interfaces.dtos.consent.v1.ConsentCatalogDTO;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.consent.v1.ConsentCatalog;
import com.catalis.masters.models.repositories.consent.v1.ConsentCatalogRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConsentCatalogServiceImplTest {

    @Mock
    private ConsentCatalogRepository repository;

    @Mock
    private ConsentCatalogMapper mapper;

    @InjectMocks
    private ConsentCatalogServiceImpl service;

    private ConsentCatalog entity;
    private ConsentCatalogDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        
        entity = new ConsentCatalog();
        entity.setConsentId(1L);
        entity.setConsentType("MARKETING");
        entity.setConsentDescription("Marketing communications consent");
        entity.setExpiryPeriodDays(365);
        entity.setConsentVersion("1.0");
        entity.setConsentSource("WEBSITE");
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(now);
        entity.setDateUpdated(now);

        dto = new ConsentCatalogDTO();
        dto.setConsentId(1L);
        dto.setConsentType("MARKETING");
        dto.setConsentDescription("Marketing communications consent");
        dto.setExpiryPeriodDays(365);
        dto.setConsentVersion("1.0");
        dto.setConsentSource("WEBSITE");
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listConsentCatalog_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(ConsentCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<ConsentCatalogDTO>> result = service.listConsentCatalog(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getConsentId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void listConsentCatalogByType_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findByConsentType(anyString(), any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(ConsentCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<ConsentCatalogDTO>> result = service.listConsentCatalogByType("MARKETING", paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getConsentId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findByConsentType(anyString(), any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void createConsentCatalog_ShouldReturnCreatedConsentCatalog() {
        // Arrange
        when(mapper.toEntity(any(ConsentCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(ConsentCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ConsentCatalog.class))).thenReturn(dto);

        // Act
        Mono<ConsentCatalogDTO> result = service.createConsentCatalog(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(ConsentCatalogDTO.class));
        verify(repository).save(any(ConsentCatalog.class));
        verify(mapper).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void getConsentCatalog_ShouldReturnConsentCatalogWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ConsentCatalog.class))).thenReturn(dto);

        // Act
        Mono<ConsentCatalogDTO> result = service.getConsentCatalog(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void getConsentCatalog_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<ConsentCatalogDTO> result = service.getConsentCatalog(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void updateConsentCatalog_ShouldReturnUpdatedConsentCatalogWhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(ConsentCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(ConsentCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(ConsentCatalog.class))).thenReturn(dto);

        // Act
        Mono<ConsentCatalogDTO> result = service.updateConsentCatalog(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(ConsentCatalogDTO.class));
        verify(repository).save(any(ConsentCatalog.class));
        verify(mapper).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void updateConsentCatalog_ShouldReturnEmptyWhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<ConsentCatalogDTO> result = service.updateConsentCatalog(1L, dto);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(ConsentCatalogDTO.class));
        verify(repository, never()).save(any(ConsentCatalog.class));
        verify(mapper, never()).toDTO(any(ConsentCatalog.class));
    }

    @Test
    void deleteConsentCatalog_ShouldDeleteWhenFound() {
        // Arrange
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteConsentCatalog(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).deleteById(anyLong());
    }
}
