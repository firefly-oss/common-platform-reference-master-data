package com.firefly.masters.models.entities.legal.v1;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("legal_form")
public class LegalForm {

    @Id
    @Column("legal_form_id")
    private Long legalFormId;

    @Column("country_id")
    private Long countryId;  // References countries(country_id)

    @Column("code")
    private String code;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("is_corporate")
    private Boolean isCorporate;

    @Column("entity_type")
    private String entityType;  // Enum values: 'FOR_PROFIT', 'NON_PROFIT', 'GOVERNMENT', 'OTHER'

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}