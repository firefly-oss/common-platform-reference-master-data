package com.firefly.masters.models.entities.lookup.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("lookup_item")
public class LookupItem {

    @Id
    @Column("item_id")
    private UUID itemId;

    @Column("domain_id")
    private UUID domainId;  // References lookup_domain(domain_id)

    @Column("item_code")
    private String itemCode;

    @Column("item_label_default")
    private String itemLabelDefault;

    @Column("item_desc")
    private String itemDesc;

    @Column("parent_item_id")
    private UUID parentItemId;  // Self-referencing to lookup_item(item_id)

    @Column("sort_order")
    private Integer sortOrder;

    @Column("effective_from")
    private LocalDate effectiveFrom;

    @Column("effective_to")
    private LocalDate effectiveTo;

    @Column("is_current")
    private Boolean isCurrent;

    @Column("extra_json")
    private String extraJson;  // Stored as JSONB in the database

    @Column("tenant_id")
    private UUID tenantId;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}