package com.firefly.masters.core.services.notification.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.masters.core.mappers.notification.v1.MessageTypeCatalogMapper;
import com.firefly.masters.core.utils.TestPaginationRequest;
import com.firefly.masters.interfaces.dtos.notification.v1.MessageTypeCatalogDTO;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.firefly.masters.models.entities.notification.v1.MessageTypeCatalog;
import com.firefly.masters.models.repositories.notification.v1.MessageTypeCatalogRepository;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MessageTypeCatalogServiceImplTest {

    @Mock
    private MessageTypeCatalogRepository repository;

    @Mock
    private MessageTypeCatalogMapper mapper;

    @InjectMocks
    private MessageTypeCatalogServiceImpl service;

    private MessageTypeCatalog entity;
    private MessageTypeCatalogDTO dto;
    private TestPaginationRequest paginationRequest;

    @BeforeEach
    void setUp() {
        // Setup test data
        LocalDateTime now = LocalDateTime.now();
        
        entity = new MessageTypeCatalog();
        entity.setTypeId(1L);
        entity.setTypeCode("EMAIL");
        entity.setTypeName("Email Message");
        entity.setDescription("Notification messages sent via email");
        entity.setStatus(StatusEnum.ACTIVE);
        entity.setDateCreated(now);
        entity.setDateUpdated(now);

        dto = new MessageTypeCatalogDTO();
        dto.setTypeId(1L);
        dto.setTypeCode("EMAIL");
        dto.setTypeName("Email Message");
        dto.setDescription("Notification messages sent via email");
        dto.setStatus(StatusEnum.ACTIVE);
        dto.setDateCreated(now);
        dto.setDateUpdated(now);

        paginationRequest = new TestPaginationRequest(0, 10);
    }

    @Test
    void listMessageTypes_ShouldReturnPaginatedResponse() {
        // Arrange
        when(repository.findAllBy(any(Pageable.class))).thenReturn(Flux.just(entity));
        when(repository.count()).thenReturn(Mono.just(1L));
        when(mapper.toDTO(any(MessageTypeCatalog.class))).thenReturn(dto);

        // Act
        Mono<PaginationResponse<MessageTypeCatalogDTO>> result = service.listMessageTypes(paginationRequest);

        // Assert
        StepVerifier.create(result)
                .expectNextMatches(response -> 
                        response.getContent().size() == 1 &&
                        response.getTotalElements() == 1L &&
                        response.getContent().get(0).getTypeCode().equals("EMAIL"))
                .verifyComplete();

        verify(repository).findAllBy(any(Pageable.class));
        verify(repository).count();
        verify(mapper).toDTO(any(MessageTypeCatalog.class));
    }

    @Test
    void createMessageType_ShouldReturnCreatedMessageType() {
        // Arrange
        when(mapper.toEntity(any(MessageTypeCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(MessageTypeCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(MessageTypeCatalog.class))).thenReturn(dto);

        // Act
        Mono<MessageTypeCatalogDTO> result = service.createMessageType(dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(MessageTypeCatalogDTO.class));
        verify(repository).save(any(MessageTypeCatalog.class));
        verify(mapper).toDTO(any(MessageTypeCatalog.class));
    }

    @Test
    void getMessageType_ShouldReturnMessageType_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(MessageTypeCatalog.class))).thenReturn(dto);

        // Act
        Mono<MessageTypeCatalogDTO> result = service.getMessageType(1L);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toDTO(any(MessageTypeCatalog.class));
    }

    @Test
    void getMessageTypeByCode_ShouldReturnMessageType_WhenFound() {
        // Arrange
        when(repository.findByTypeCode(anyString())).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(MessageTypeCatalog.class))).thenReturn(dto);

        // Act
        Mono<MessageTypeCatalogDTO> result = service.getMessageTypeByCode("EMAIL");

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByTypeCode(anyString());
        verify(mapper).toDTO(any(MessageTypeCatalog.class));
    }

    @Test
    void updateMessageType_ShouldReturnUpdatedMessageType_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(mapper.toEntity(any(MessageTypeCatalogDTO.class))).thenReturn(entity);
        when(repository.save(any(MessageTypeCatalog.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(MessageTypeCatalog.class))).thenReturn(dto);

        // Act
        Mono<MessageTypeCatalogDTO> result = service.updateMessageType(1L, dto);

        // Assert
        StepVerifier.create(result)
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(mapper).toEntity(any(MessageTypeCatalogDTO.class));
        verify(repository).save(any(MessageTypeCatalog.class));
        verify(mapper).toDTO(any(MessageTypeCatalog.class));
    }

    @Test
    void deleteMessageType_ShouldDeleteMessageType_WhenFound() {
        // Arrange
        when(repository.findById(anyLong())).thenReturn(Mono.just(entity));
        when(repository.delete(any(MessageTypeCatalog.class))).thenReturn(Mono.empty());

        // Act
        Mono<Void> result = service.deleteMessageType(1L);

        // Assert
        StepVerifier.create(result)
                .verifyComplete();

        verify(repository).findById(anyLong());
        verify(repository).delete(any(MessageTypeCatalog.class));
    }
}
