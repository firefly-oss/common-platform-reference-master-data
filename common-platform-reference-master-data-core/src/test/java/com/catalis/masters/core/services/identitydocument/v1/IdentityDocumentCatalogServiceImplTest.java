package com.catalis.masters.core.services.identitydocument.v1;

import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.core.mappers.identitydocument.v1.IdentityDocumentCatalogMapper;
import com.catalis.masters.core.utils.TestPaginationRequest;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCatalogDTO;
import com.catalis.masters.interfaces.dtos.identitydocument.v1.IdentityDocumentCategoryCatalogDTO;
import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentCatalog;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentCatalogRepository;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentLocalizationRepository;
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
public class IdentityDocumentCatalogServiceImplTest {

    @Mock
    private IdentityDocumentCatalogRepository repository;

    @Mock
    private IdentityDocumentLocalizationRepository localizationRepository;

    @Mock
    private IdentityDocumentCatalogMapper mapper;

    @InjectMocks
    private IdentityDocumentCatalogServiceImpl service;

    private IdentityDocumentCatalog entity;
    private IdentityDocumentCatalogDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        
        entity = new IdentityDocumentCatalog();
        entity.setDocumentId(1L);
        entity.setDocumentCode("PASSPORT");
        entity.setDocumentName("Passport");
        entity.setCategoryId(1L);
        entity.setCountryId(724L); // Spain
        entity.setDescription("International passport for travel and identification");
        entity.setValidationRegex("^[A-Z0-9]{9}$");
        entity.setFormatDescription("9 characters, alphanumeric");
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(now);
        entity.setDateUpdated(now);

        IdentityDocumentCategoryCatalogDTO categoryDTO = new IdentityDocumentCategoryCatalogDTO();
        categoryDTO.setCategoryId(1L);
        categoryDTO.setCategoryCode("GOVERNMENT");
        categoryDTO.setCategoryName("Government");
        categoryDTO.setDescription("Government-issued identification documents");
        categoryDTO.setStatus(StatusEnum.ACTIVE);
        categoryDTO.setDateCreated(now);
        categoryDTO.setDateUpdated(now);

        dto = new IdentityDocumentCatalogDTO();
        dto.setDocumentId(1L);
        dto.setDocumentCode("PASSPORT");
        dto.setDocumentName("Passport");
        dto.setCategoryId(1L);
        dto.setCategory(categoryDTO);
        dto.setCountryId(724L);
        dto.setDescription("International passport for travel and identification");
        dto.setValidationRegex("^[A-Z0-9]{9}$");
        dto.setFormatDescription("9 characters, alphanumeric");
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listIdentityDocuments_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<IdentityDocumentCatalogDTO>> result = service.listIdentityDocuments(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getDocumentId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void listIdentityDocumentsByCategory_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findByCategoryId(anyLong(), any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.countByCategoryId(anyLong())).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<IdentityDocumentCatalogDTO>> result = service.listIdentityDocumentsByCategory(1L, paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getDocumentId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findByCategoryId(anyLong(), any(Pageable.class));
        verify(repository).countByCategoryId(anyLong());
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void listIdentityDocumentsByCountry_ShouldReturnPaginatedResponse() {
        // Arrange
        Pageable pageable = paginationRequest.toPageable();
        when(repository.findByCountryId(anyLong(), any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.countByCountryId(anyLong())).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<IdentityDocumentCatalogDTO>> result = service.listIdentityDocumentsByCountry(724L, paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> {
                    return response.getContent().size() == 1 &&
                            response.getContent().get(0).getDocumentId().equals(1L) &&
                            response.getTotalElements() == 1L;
                })
                .verifyComplete();

        verify(repository).findByCountryId(anyLong(), any(Pageable.class));
        verify(repository).countByCountryId(anyLong());
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void createIdentityDocument_ShouldReturnCreatedDocument() {
        // Arrange
        when(mapper.toEntity(any(IdentityDocumentCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(IdentityDocumentCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.createIdentityDocument(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(IdentityDocumentCatalogDTO.class));
        verify(repository).save(any(IdentityDocumentCatalog.class));
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void getIdentityDocument_ShouldReturnDocument_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.getIdentityDocument(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void getIdentityDocument_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.getIdentityDocument(1L);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document not found with ID: 1"))
                .verify();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void getIdentityDocumentByCode_ShouldReturnDocument_WhenFound() {
        // Arrange
        when(repository.findByDocumentCode(anyString())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.getIdentityDocumentByCode("PASSPORT");

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByDocumentCode(anyString());
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void getIdentityDocumentByCode_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findByDocumentCode(anyString())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.getIdentityDocumentByCode("UNKNOWN");

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document not found with code: UNKNOWN"))
                .verify();

        verify(repository).findByDocumentCode(anyString());
        verify(mapper, never()).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void updateIdentityDocument_ShouldReturnUpdatedDocument_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(IdentityDocumentCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(IdentityDocumentCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(IdentityDocumentCatalog.class))).thenReturn(dto);

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.updateIdentityDocument(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(IdentityDocumentCatalogDTO.class));
        verify(repository).save(any(IdentityDocumentCatalog.class));
        verify(mapper).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void updateIdentityDocument_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentCatalogDTO> result = service.updateIdentityDocument(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document not found with ID: 1"))
                .verify();

        verify(repository).findById(anyLong());
        verify(mapper, never()).toEntity(any(IdentityDocumentCatalogDTO.class));
        verify(repository, never()).save(any(IdentityDocumentCatalog.class));
        verify(mapper, never()).toDTO(any(IdentityDocumentCatalog.class));
    }

    @Test
    void deleteIdentityDocument_ShouldDeleteDocument_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(localizationRepository.deleteByDocumentId(anyLong())).thenReturn(Mono.empty());
        when(repository.deleteById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteIdentityDocument(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(localizationRepository).deleteByDocumentId(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteIdentityDocument_ShouldReturnError_WhenNotFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteIdentityDocument(1L);

        // Assert
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().contains("Identity document not found with ID: 1"))
                .verify();

        verify(repository).findById(anyLong());
        verify(localizationRepository, never()).deleteByDocumentId(anyLong());
        verify(repository, never()).deleteById(anyLong());
    }
}
