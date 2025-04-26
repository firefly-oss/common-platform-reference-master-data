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
 * Data Transfer Object for notification message template information.
 * Used for transferring notification message template data between different layers of the application.
 * Includes support for template variables that can be used for dynamic content generation.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageTemplateDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long templateId;

    private Long messageId;
    private String templateName;
    private String templateContent;
    private String templateType;
    private String version;
    private Map<String, Object> templateVariables;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
