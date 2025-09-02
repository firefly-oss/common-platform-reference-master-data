package com.firefly.masters.interfaces.dtos.lookup.v1;

import com.firefly.annotations.ValidDate;
import com.firefly.core.utils.annotations.FilterableId;
import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LookupItemDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID itemId;

    @FilterableId
    @NotNull(message = "Domain ID is required")
    private UUID domainId;

    @NotBlank(message = "Item code is required")
    @Size(max = 50, message = "Item code must not exceed 50 characters")
    private String itemCode;

    @NotBlank(message = "Item label is required")
    @Size(max = 200, message = "Item label must not exceed 200 characters")
    private String itemLabelDefault;

    @Size(max = 500, message = "Item description must not exceed 500 characters")
    private String itemDesc;

    private UUID parentItemId;

    @Min(value = 0, message = "Sort order must be non-negative")
    private Integer sortOrder;

    @ValidDate
    private LocalDate effectiveFrom;

    @ValidDate
    private LocalDate effectiveTo;

    private Boolean isCurrent;

    @Size(max = 2000, message = "Extra JSON must not exceed 2000 characters")
    private String extraJson;

    private UUID tenantId;

    @NotNull(message = "Status is required")
    private StatusEnum status;
}
