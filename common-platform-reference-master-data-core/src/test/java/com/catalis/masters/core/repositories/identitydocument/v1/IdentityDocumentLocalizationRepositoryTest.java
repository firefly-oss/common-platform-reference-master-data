package com.catalis.masters.core.repositories.identitydocument.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.catalis.masters.models.entities.identitydocument.v1.IdentityDocumentLocalization;
import com.catalis.masters.models.repositories.identitydocument.v1.IdentityDocumentLocalizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IdentityDocumentLocalizationRepositoryTest {

    @Mock
    private IdentityDocumentLocalizationRepository repository;

    private IdentityDocumentLocalization entity;
    private Pageable pageable;

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

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void findByDocumentId_ShouldReturnLocalizations() {
        // Arrange
        when(repository.findByDocumentId(anyLong(), any(Pageable.class))).thenReturn(Flux.just(entity));

        // Act
        Flux<IdentityDocumentLocalization> result = repository.findByDocumentId(1L, pageable);

        // Assert
        StepVerifier.create(result)
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    void countByDocumentId_ShouldReturnCount() {
        // Arrange
        when(repository.countByDocumentId(anyLong())).thenReturn(Mono.just(1L));

        // Act
        Mono<Long> result = repository.countByDocumentId(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    void findByDocumentIdAndLocaleId_ShouldReturnLocalization_WhenFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(anyLong(), anyLong())).thenReturn(Mono.just(entity));

        // Act
        Mono<IdentityDocumentLocalization> result = repository.findByDocumentIdAndLocaleId(1L, 1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    void findByDocumentIdAndLocaleId_ShouldReturnEmpty_WhenNotFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(anyLong(), anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentLocalization> result = repository.findByDocumentIdAndLocaleId(1L, 2L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteByDocumentId_ShouldDeleteLocalizations() {
        // Arrange
        when(repository.deleteByDocumentId(anyLong())).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = repository.deleteByDocumentId(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }
}
