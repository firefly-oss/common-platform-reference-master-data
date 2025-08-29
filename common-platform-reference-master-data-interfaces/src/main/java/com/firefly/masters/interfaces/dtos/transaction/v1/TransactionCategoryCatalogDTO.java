package com.firefly.masters.interfaces.dtos.transaction.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for transaction category catalog information.
 * Used for transferring transaction category catalog data between different layers of the application.
 * Supports hierarchical structure with parent-child relationships.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long categoryId;

    private String categoryCode;
    private String categoryName;
    private String description;
    private Long parentCategoryId;
    private TransactionCategoryCatalogDTO parentCategory;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String svgIcon;
}
