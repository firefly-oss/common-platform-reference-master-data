package com.firefly.masters.models.entities.transaction.v1;

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
 * Entity representing a transaction category catalog record.
 * This defines the categories that can be used for transaction categorization.
 * Supports hierarchical structure with parent-child relationships.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("transaction_category_catalog")
public class TransactionCategoryCatalog {

    @Id
    @Column("category_id")
    private Long categoryId;

    @Column("category_code")
    private String categoryCode;

    @Column("category_name")
    private String categoryName;

    @Column("description")
    private String description;

    @Column("parent_category_id")
    private Long parentCategoryId;  // Self-referencing to transaction_category_catalog(category_id)

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;

    @Column("svg_icon")
    private String svgIcon;
}
