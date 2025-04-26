package com.catalis.masters.core.services.notification.v1;

import com.catalis.masters.core.mappers.notification.v1.NotificationMessageLocalizationMapper;
import com.catalis.masters.interfaces.dtos.notification.v1.NotificationMessageLocalizationDTO;
import com.catalis.masters.models.entities.notification.v1.NotificationMessageLocalization;
import com.catalis.masters.models.repositories.notification.v1.NotificationMessageLocalizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the NotificationMessageLocalizationService interface.
 */
@Service
@Transactional
public class NotificationMessageLocalizationServiceImpl implements NotificationMessageLocalizationService {

    @Autowired
    private NotificationMessageLocalizationRepository repository;

    @Autowired
    private NotificationMessageLocalizationMapper mapper;

    @Override
    public Flux<NotificationMessageLocalizationDTO> getLocalizationsByMessageId(Long messageId) {
        return repository.findByMessageId(messageId)
                .map(mapper::toDTO)
                .switchIfEmpty(Flux.error(new RuntimeException("No localizations found for message ID: " + messageId)));
    }

    @Override
    public Flux<NotificationMessageLocalizationDTO> getLocalizationsByLocaleId(Long localeId) {
        return repository.findByLocaleId(localeId)
                .map(mapper::toDTO)
                .switchIfEmpty(Flux.error(new RuntimeException("No localizations found for locale ID: " + localeId)));
    }

    @Override
    public Mono<NotificationMessageLocalizationDTO> createNotificationMessageLocalization(NotificationMessageLocalizationDTO localizationDTO) {
        // Set audit fields
        LocalDateTime now = LocalDateTime.now();
        localizationDTO.setDateCreated(now);
        localizationDTO.setDateUpdated(now);

        return Mono.just(localizationDTO)
                .map(mapper::toEntity)
                .flatMap(repository::save)
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error creating notification message localization: " + e.getMessage(), e)));
    }

    @Override
    public Mono<NotificationMessageLocalizationDTO> getNotificationMessageLocalization(Long localizationId) {
        return repository.findById(localizationId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message localization not found with ID: " + localizationId)));
    }

    @Override
    public Mono<NotificationMessageLocalizationDTO> getNotificationMessageLocalizationByMessageAndLocale(Long messageId, Long localeId) {
        return repository.findByMessageIdAndLocaleId(messageId, localeId)
                .map(mapper::toDTO)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message localization not found with message ID: " + messageId + " and locale ID: " + localeId)));
    }

    @Override
    public Mono<NotificationMessageLocalizationDTO> updateNotificationMessageLocalization(Long localizationId, NotificationMessageLocalizationDTO localizationDTO) {
        return repository.findById(localizationId)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message localization not found with ID: " + localizationId)))
                .flatMap(existingEntity -> {
                    NotificationMessageLocalization updatedEntity = mapper.toEntity(localizationDTO);
                    updatedEntity.setLocalizationId(localizationId);
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    updatedEntity.setDateUpdated(LocalDateTime.now());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error updating notification message localization: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteNotificationMessageLocalization(Long localizationId) {
        return repository.findById(localizationId)
                .switchIfEmpty(Mono.error(new RuntimeException("Notification message localization not found with ID: " + localizationId)))
                .flatMap(repository::delete)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting notification message localization: " + e.getMessage(), e)));
    }

    @Override
    public Mono<Void> deleteLocalizationsByMessageId(Long messageId) {
        return repository.deleteByMessageId(messageId)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error deleting localizations for message ID: " + messageId, e)));
    }
}
