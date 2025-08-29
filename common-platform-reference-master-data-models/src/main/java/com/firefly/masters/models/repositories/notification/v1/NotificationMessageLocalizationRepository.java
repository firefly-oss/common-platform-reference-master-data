package com.firefly.masters.models.repositories.notification.v1;

import com.firefly.masters.models.entities.notification.v1.NotificationMessageLocalization;
import com.firefly.masters.models.repositories.BaseRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for managing NotificationMessageLocalization entities.
 * Extends BaseRepository to inherit common CRUD operations.
 */
@Repository
public interface NotificationMessageLocalizationRepository extends BaseRepository<NotificationMessageLocalization, Long> {

    /**
     * Find all localizations for a specific message.
     *
     * @param messageId the ID of the notification message
     * @return a Flux of NotificationMessageLocalization entities for the specified message
     */
    Flux<NotificationMessageLocalization> findByMessageId(Long messageId);

    /**
     * Count localizations for a specific message.
     *
     * @param messageId the ID of the notification message
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM notification_message_localization WHERE message_id = :messageId")
    Mono<Long> countByMessageId(Long messageId);

    /**
     * Find all localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Flux of NotificationMessageLocalization entities for the specified locale
     */
    Flux<NotificationMessageLocalization> findByLocaleId(Long localeId);

    /**
     * Count localizations for a specific locale.
     *
     * @param localeId the ID of the language locale
     * @return a Mono of Long representing the count
     */
    @Query("SELECT COUNT(*) FROM notification_message_localization WHERE locale_id = :localeId")
    Mono<Long> countByLocaleId(Long localeId);

    /**
     * Find a specific localization by message ID and locale ID.
     *
     * @param messageId the ID of the notification message
     * @param localeId the ID of the language locale
     * @return a Mono of NotificationMessageLocalization
     */
    Mono<NotificationMessageLocalization> findByMessageIdAndLocaleId(Long messageId, Long localeId);

    /**
     * Delete all localizations for a specific message.
     *
     * @param messageId the ID of the notification message
     * @return a Mono of Void
     */
    Mono<Void> deleteByMessageId(Long messageId);
}
