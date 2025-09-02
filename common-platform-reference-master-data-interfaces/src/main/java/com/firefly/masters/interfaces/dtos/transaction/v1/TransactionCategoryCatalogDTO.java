package com.firefly.masters.interfaces.dtos.transaction.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID categoryId;

    @NotBlank(message = "Category code is required")
    @Size(max = 20, message = "Category code must not exceed 20 characters")
    private String categoryCode;

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private String categoryName;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    private UUID parentCategoryId;
    private TransactionCategoryCatalogDTO parentCategory;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    @Size(max = 10000, message = "SVG icon data must not exceed 10000 characters")
    private String svgIcon;
}
