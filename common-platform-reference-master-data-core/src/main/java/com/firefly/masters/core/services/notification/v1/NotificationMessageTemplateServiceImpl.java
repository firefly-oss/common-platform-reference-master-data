package com.firefly.masters.core.services.notification.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.masters.core.mappers.notification.v1.NotificationMessageTemplateMapper;
import com.firefly.masters.interfaces.dtos.notification.v1.NotificationMessageTemplateDTO;
import com.firefly.masters.models.entities.notification.v1.NotificationMessageTemplate;
import com.firefly.masters.models.repositories.notification.v1.NotificationMessageTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the NotificationMessageTemplateService interface.
 */
@Service
@Transactional
public class NotificationMessageTemplateServiceImpl implements NotificationMessageTemplateService {

    @Autowired
    private NotificationMessageTemplateRepository repository;

    @Autowired
    private NotificationMessageTemplateMapper mapper;

    @Override
    public Mono<PaginationResponse<NotificationMessageTemplateDTO>> listNotificationMessageTemplates(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findAllBy(pageable),
                () -> repository.count()
        );
    }

    @Override
    public Mono<PaginationResponse<NotificationMessageTemplateDTO>> listNotificationMessageTemplatesByType(String templateType, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByTemplateType(templateType, pageable),
                () -> repository.countByTemplateType(templateType)
        );
    }

    @Override
    public Flux<NotificationMessageTemplateDTO> getTemplatesByMessageId(Long messageId) {
        return repository.findByMessageId(messageId)
                .map(mapper::toDTO)
                .switchIfEmpty(Flux.error(new RuntimeException("No templates found for message ID: " + messageId)));
    }

    @Override
    public Mono<NotificationMessageTemplateDTO> createNotificationMessageTemplate(NotificationMessageTemplateDTO templateDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        templateDTO.setDateCreated(now);
        templateDTO.setDateUpdated(now);

        return Mono.just(templateDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating notification message template: " + e.getMessage(), e)));
    }

    @Override
    public Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplate(Long templateId) {
        return repository.findById(templateId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message template not found with ID: " + templateId)));
    }

    @Override
    public Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplateByNameAndMessageId(Long messageId, String templateName) {
        return repository.findByMessageIdAndTemplateName(messageId, templateName)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message template not found with message ID: " + messageId + " and template name: " + templateName)));
    }

    @Override
    public Mono<NotificationMessageTemplateDTO> updateNotificationMessageTemplate(Long templateId, NotificationMessageTemplateDTO templateDTO) {
        return repository.findById(templateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message template not found with ID: " + templateId)))
                .flatMap(existingEntity -> {
                    NotificationMessageTemplate updatedEntity = mapper.toEntity(templateDTO);
                    updatedEntity.setTemplateId(templateId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating notification message template: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteNotificationMessageTemplate(Long templateId) {
        return repository.findById(templateId)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message template not found with ID: " + templateId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting notification message template: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteTemplatesByMessageId(Long messageId) {
        return repository.deleteByMessageId(messageId)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting templates for message ID: " + messageId, e)));
    }
}
