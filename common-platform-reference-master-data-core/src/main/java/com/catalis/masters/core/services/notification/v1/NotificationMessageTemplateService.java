package com.catalis.masters.core.services.notification.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.masters.interfaces.dtos.notification.v1.NotificationMessageTemplateDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing notification message template operations.
 */
public interface NotificationMessageTemplateService {

    /**
     * List all notification message templates with pagination.
     *
     * @param paginationRequest pagination parameters
     * @return a paginated response of notification message template DTOs
     */
    Mono<PaginationResponse<NotificationMessageTemplateDTO>> listNotificationMessageTemplates(PaginationRequest paginationRequest);

    /**
     * List notification message templates by template type with pagination.
     *
     * @param templateType the type of template
     * @param paginationRequest pagination parameters
     * @return a paginated response of notification message template DTOs
     */
    Mono<PaginationResponse<NotificationMessageTemplateDTO>> listNotificationMessageTemplatesByType(String templateType, PaginationRequest paginationRequest);

    /**
     * Get all templates for a specific message.
     *
     * @param messageId the ID of the notification message
     * @return a Flux of notification message template DTOs
     */
    Flux<NotificationMessageTemplateDTO> getTemplatesByMessageId(Long messageId);

    /**
     * Create a new notification message template.
     *
     * @param templateDTO the notification message template data
     * @return the created notification message template DTO
     */
    Mono<NotificationMessageTemplateDTO> createNotificationMessageTemplate(NotificationMessageTemplateDTO templateDTO);

    /**
     * Get a notification message template by ID.
     *
     * @param templateId the ID of the notification message template
     * @return the notification message template DTO
     */
    Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplate(Long templateId);

    /**
     * Get a notification message template by message ID and template name.
     *
     * @param messageId the ID of the notification message
     * @param templateName the name of the template
     * @return the notification message template DTO
     */
    Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplateByNameAndMessageId(Long messageId, String templateName);

    /**
     * Update a notification message template.
     *
     * @param templateId the ID of the notification message template to update
     * @param templateDTO the updated notification message template data
     * @return the updated notification message template DTO
     */
    Mono<NotificationMessageTemplateDTO> updateNotificationMessageTemplate(Long templateId, NotificationMessageTemplateDTO templateDTO);

    /**
     * Delete a notification message template.
     *
     * @param templateId the ID of the notification message template to delete
     * @return a Mono of Void
     */
    Mono<Void> deleteNotificationMessageTemplate(Long templateId);

    /**
     * Delete all templates for a specific message.
     *
     * @param messageId the ID of the notification message
     * @return a Mono of Void
     */
    Mono<Void> deleteTemplatesByMessageId(Long messageId);
}
