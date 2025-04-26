package com.catalis.masters.interfaces.dtos.notification.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Data Transfer Object for notification message catalog information.
 * Used for transferring notification message catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long messageId;

    private String messageCode;
    private Long typeId;
    private MessageTypeCatalogDTO messageType;
    private String eventType;
    private String description;
    private String defaultSubject;
    private String defaultMessage;
    private Map<String, Object> parameters;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
