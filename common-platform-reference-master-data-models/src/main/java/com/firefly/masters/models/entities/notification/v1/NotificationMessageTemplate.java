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
import java.util.UUID;

/**
 * Entity representing a notification message template.
 * This stores HTML templates for notification messages with support for template variables.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("notification_message_template")
public class NotificationMessageTemplate {

    @Id
    @Column("template_id")
    private UUID templateId;

    @Column("message_id")
    private UUID messageId;  // References notification_message_catalog(message_id)

    @Column("template_name")
    private String templateName;

    @Column("template_content")
    private String templateContent;

    @Column("template_type")
    private String templateType;

    @Column("version")
    private String version;

    @Column("template_variables")
    private String templateVariables;  // Stored as JSONB in the database

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
