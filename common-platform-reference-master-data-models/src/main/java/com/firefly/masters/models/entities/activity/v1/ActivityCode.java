package com.firefly.masters.models.entities.activity.v1;

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
@Table("activity_code")
public class ActivityCode {

    @Id
    @Column("activity_code_id")
    private Long activityCodeId;

    @Column("country_id")
    private Long countryId;  // References countries(country_id)

    @Column("code")
    private String code;

    @Column("classification_sys")
    private String classificationSys;

    @Column("description")
    private String description;

    @Column("parent_code_id")
    private Long parentCodeId;  // Self-referencing to activity_code(activity_code_id)

    @Column("high_risk")
    private Boolean highRisk;

    @Column("risk_factors")
    private String riskFactors;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}