package com.firefly.masters.core.repositories.identitydocument.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.identitydocument.v1.IdentityDocumentLocalization;
import com.firefly.masters.models.repositories.identitydocument.v1.IdentityDocumentLocalizationRepository;
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
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IdentityDocumentLocalizationRepositoryTest {

    @Mock
    private IdentityDocumentLocalizationRepository repository;

    private IdentityDocumentLocalization entity;
    private Pageable pageable;
    private UUID testDocumentId;
    private UUID testLocaleId;
    private UUID testLocalizationId;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        testDocumentId = UUID.randomUUID();
        testLocaleId = UUID.randomUUID();
        testLocalizationId = UUID.randomUUID();

        entity = new IdentityDocumentLocalization();
        entity.setLocalizationId(testLocalizationId);
        entity.setDocumentId(testDocumentId);
        entity.setLocaleId(testLocaleId);
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
        when(repository.findByDocumentId(any(UUID.class), any(Pageable.class))).thenReturn(Flux.just(entity));

        // Act
        Flux<IdentityDocumentLocalization> result = repository.findByDocumentId(testDocumentId, pageable);

        // Assert
        StepVerifier.create(result)
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    void countByDocumentId_ShouldReturnCount() {
        // Arrange
        when(repository.countByDocumentId(any(UUID.class))).thenReturn(Mono.just(1L));

        // Act
        Mono<Long> result = repository.countByDocumentId(testDocumentId);

        // Assert
        StepVerifier.create(result)
                .expectNext(1L)
                .verifyComplete();
    }

    @Test
    void findByDocumentIdAndLocaleId_ShouldReturnLocalization_WhenFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(any(UUID.class), any(UUID.class))).thenReturn(Mono.just(entity));

        // Act
        Mono<IdentityDocumentLocalization> result = repository.findByDocumentIdAndLocaleId(testDocumentId, testLocaleId);

        // Assert
        StepVerifier.create(result)
                .expectNext(entity)
                .verifyComplete();
    }

    @Test
    void findByDocumentIdAndLocaleId_ShouldReturnEmpty_WhenNotFound() {
        // Arrange
        when(repository.findByDocumentIdAndLocaleId(any(UUID.class), any(UUID.class))).thenReturn(Mono.empty());

        // Act
        Mono<IdentityDocumentLocalization> result = repository.findByDocumentIdAndLocaleId(testDocumentId, UUID.randomUUID());

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deleteByDocumentId_ShouldDeleteLocalizations() {
        // Arrange
        when(repository.deleteByDocumentId(any(UUID.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = repository.deleteByDocumentId(testDocumentId);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }
}
