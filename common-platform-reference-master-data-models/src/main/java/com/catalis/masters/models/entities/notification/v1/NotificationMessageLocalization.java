package com.catalis.masters.models.entities.notification.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entity representing a localized version of a notification message.
 * This stores translations of notification messages for different languages/locales.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("notification_message_localization")
public class NotificationMessageLocalization {

    @Id
    @Column("localization_id")
    private Long localizationId;

    @Column("message_id")
    private Long messageId;  // References notification_message_catalog(message_id)

    @Column("locale_id")
    private Long localeId;  // References language_locale(locale_id)

    @Column("subject")
    private String subject;

    @Column("message")
    private String message;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
