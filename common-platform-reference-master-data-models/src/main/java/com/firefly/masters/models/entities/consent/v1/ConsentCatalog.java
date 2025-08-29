package com.firefly.masters.models.entities.consent.v1;

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
 * Entity representing a consent catalog record.
 * This defines the types of consents that can be given by users, such as GDPR, Marketing Communications, etc.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("consent_catalog")
public class ConsentCatalog {

    @Id
    @Column("consent_id")
    private Long consentId;

    @Column("consent_type")
    private String consentType;

    @Column("consent_description")
    private String consentDescription;

    @Column("expiry_period_days")
    private Integer expiryPeriodDays;

    @Column("consent_version")
    private String consentVersion;

    @Column("consent_source")
    private String consentSource;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}