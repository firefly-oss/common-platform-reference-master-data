package com.catalis.masters.core.services.notification.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.masters.core.mappers.notification.v1.MessageTypeCatalogMapper;
import com.catalis.masters.interfaces.dtos.notification.v1.MessageTypeCatalogDTO;
import com.catalis.masters.models.entities.notification.v1.MessageTypeCatalog;
import com.catalis.masters.models.repositories.notification.v1.MessageTypeCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the MessageTypeCatalogService interface.
 */
@Service
@Transactional
public class MessageTypeCatalogServiceImpl implements MessageTypeCatalogService {

    @Autowired
    private MessageTypeCatalogRepository repository;

    @Autowired
    private MessageTypeCatalogMapper mapper;

    @Override
    public Mono<PaginationResponse<MessageTypeCatalogDTO>> listMessageTypes(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<MessageTypeCatalogDTO> createMessageType(MessageTypeCatalogDTO messageTypeDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        messageTypeDTO.setDateCreated(now);
        messageTypeDTO.setDateUpdated(now);

        return Mono.just(messageTypeDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating message type: " + e.getMessage(), e)));
    }

    @Override
    public Mono<MessageTypeCatalogDTO> getMessageType(Long typeId) {
        return repository.findById(typeId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Message type not found with ID: " + typeId)));
    }

    @Override
    public Mono<MessageTypeCatalogDTO> getMessageTypeByCode(String typeCode) {
        return repository.findByTypeCode(typeCode)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Message type not found with code: " + typeCode)));
    }

    @Override
    public Mono<MessageTypeCatalogDTO> updateMessageType(Long typeId, MessageTypeCatalogDTO messageTypeDTO) {
        return repository.findById(typeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Message type not found with ID: " + typeId)))
                .flatMap(existingEntity -> {
                    MessageTypeCatalog updatedEntity = mapper.toEntity(messageTypeDTO);
                    updatedEntity.setTypeId(typeId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating message type: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteMessageType(Long typeId) {
        return repository.findById(typeId)
                .switchIfEmpty(Mono.error(new RuntimeException("Message type not found with ID: " + typeId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting message type: " + e.getMessage(), e)));
    }
}
