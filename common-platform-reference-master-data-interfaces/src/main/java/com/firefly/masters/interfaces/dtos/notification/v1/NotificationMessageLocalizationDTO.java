package com.firefly.masters.interfaces.dtos.notification.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for notification message localization information.
 * Used for transferring notification message localization data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageLocalizationDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long localizationId;

    private Long messageId;
    private Long localeId;
    private String subject;
    private String message;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
