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
 * Entity representing a message type catalog record.
 * This defines the types of notification messages that can be sent (Email, SMS, Push, etc.).
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("message_type_catalog")
public class MessageTypeCatalog {

    @Id
    @Column("type_id")
    private Long typeId;

    @Column("type_code")
    private String typeCode;

    @Column("type_name")
    private String typeName;

    @Column("description")
    private String description;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
