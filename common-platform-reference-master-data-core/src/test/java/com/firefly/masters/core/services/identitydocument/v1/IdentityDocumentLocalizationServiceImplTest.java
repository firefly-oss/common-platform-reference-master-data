package com.firefly.masters.core.services.identitydocument.v1;

import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.identitydocument.v1.IdentityDocumentLocalizationMapper;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentLocalizationDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.identitydocument.v1.IdentityDocumentLocalization;
import com.firefly.masters.models.repositories.identitydocument.v1.IdentityDocumentLocalizationRepository;
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
public class IdentityDocumentLocalizationServiceImplTest {

    @Mock
    private IdentityDocumentLocalizationRepository repository;

    @Mock
    private IdentityDocumentLocalizationMapper mapper;

    @InjectMocks
    private IdentityDocumentLocalizationServiceImpl service;

    private IdentityDocumentLocalization entity;
    private IdentityDocumentLocalizationDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        
        entity = new IdentityDocumentLocalization();
        entity.setLocalizationId(1L);
        entity.setDocumentId(1L);
        entity.setLocaleId(1L); // English
        entity.setDocumentName("Passport");
        entity.setDescription("International passport for travel and identification");
        entity.setFormatDescription("9 characters, alphanumeric");
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(now);
        entity.setDateUpdated(now);

        dto = new IdentityDocumentLocalizationDTO();
        dto.setLocalizationId(1L);
        dto.setDocumentId(1L);
        dto.setLocaleId(1L);
        dto.setDocumentName("Passport");
        dto.setDescription("International passport for travel and identification");
        dto.setFormatDescription("9 characters, alphanumeric");
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listIdentityDocumentLocalizations_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(IdentityDocumentLocalization.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<IdentityDocumentLocalizationDTO>> result = service.listIdentityDocumentLocalizations(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getLocalizationId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void getLocalizationsByDocumentId_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findByDocumentId(anyLong(), any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.countByDocumentId(anyLong())).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(IdentityDocumentLocalization.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<IdentityDocumentLocalizationDTO>> result = service.getLocalizationsByDocumentId(1L, paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getLocalizationId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findByDocumentId(anyLong(), any(Pageable.class));
        verify(repository).countByDocumentId(anyLong());
        verify(mapper).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void createIdentityDocumentLocalization_ShouldReturnCreatedLocalization() {
        // Arrange
        when(mapper.toEntity(any(IdentityDocumentLocalizationDTO.class))).thenReturn(entity);
        when(repository.save(any(IdentityDocumentLocalization.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentLocalization.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentLocalizationDTO> result = service.createIdentityDocumentLocalization(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(IdentityDocumentLocalizationDTO.class));
        verify(repository).save(any(IdentityDocumentLocalization.class));
        verify(mapper).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void getIdentityDocumentLocalizationByDocumentAndLocale_ShouldReturnLocalization_WhenFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(anyLong(), anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentLocalization.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentLocalizationDTO> result = service.getIdentityDocumentLocalizationByDocumentAndLocale(1L, 1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByDocumentIdAndLocaleId(anyLong(), anyLong());
        verify(mapper).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void getIdentityDocumentLocalizationByDocumentAndLocale_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(anyLong(), anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentLocalizationDTO> result = service.getIdentityDocumentLocalizationByDocumentAndLocale(1L, 1L);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document localization not found with document ID: 1 and locale ID: 1"))
                .verify();

        verify(repository).findByDocumentIdAndLocaleId(anyLong(), anyLong());
        verify(mapper, never()).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void updateIdentityDocumentLocalization_ShouldReturnUpdatedLocalization_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(IdentityDocumentLocalizationDTO.class))).thenReturn(entity);
        when(repository.save(any(IdentityDocumentLocalization.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentLocalization.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentLocalizationDTO> result = service.updateIdentityDocumentLocalization(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(IdentityDocumentLocalizationDTO.class));
        verify(repository).save(any(IdentityDocumentLocalization.class));
        verify(mapper).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void updateIdentityDocumentLocalization_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentLocalizationDTO> result = service.updateIdentityDocumentLocalization(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document localization not found with ID: 1"))
                .verify();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(IdentityDocumentLocalizationDTO.class));
        verify(repository, never()).save(any(IdentityDocumentLocalization.class));
        verify(mapper, never()).toDTO(any(IdentityDocumentLocalization.class));
    }

    @Test
    void deleteIdentityDocumentLocalization_ShouldDeleteLocalization_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteIdentityDocumentLocalization(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteIdentityDocumentLocalization_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteIdentityDocumentLocalization(1L);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document localization not found with ID: 1"))
                .verify();

        verify(repository).findById(anyLong());
        verify(repository, never()).deleteById(anyLong());
    }

    @Test
    void deleteLocalizationsByDocumentId_ShouldDeleteLocalizations() {
        // Arrange
        when(repository.deleteByDocumentId(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteLocalizationsByDocumentId(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).deleteByDocumentId(anyLong());
    }
}
