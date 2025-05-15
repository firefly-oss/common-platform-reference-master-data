package com.catalis.masters.interfaces.dtos.transaction.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for transaction category localization information.
 * Used for transferring transaction category localization data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryLocalizationDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long localizationId;

    private Long categoryId;
    private Long localeId;
    private String categoryName;
    private String description;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
