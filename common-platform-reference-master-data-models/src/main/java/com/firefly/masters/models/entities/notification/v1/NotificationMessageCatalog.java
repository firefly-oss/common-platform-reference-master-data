package com.firefly.masters.models.entities.notification.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
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
 * Entity representing a notification message catalog record.
 * This defines the types of notification messages that can be sent to users based on events.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("notification_message_catalog")
public class NotificationMessageCatalog {

    @Id
    @Column("message_id")
    private Long messageId;

    @Column("message_code")
    private String messageCode;

    @Column("type_id")
    private Long typeId;

    @Column("event_type")
    private String eventType;

    @Column("description")
    private String description;

    @Column("default_subject")
    private String defaultSubject;

    @Column("default_message")
    private String defaultMessage;

    @Column("parameters")
    private String parameters;  // Stored as JSONB in the database

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
