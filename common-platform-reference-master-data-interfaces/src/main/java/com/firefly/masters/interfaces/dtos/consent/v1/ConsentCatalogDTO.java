package com.firefly.masters.interfaces.dtos.consent.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for consent catalog information.
 * Used for transferring consent catalog data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsentCatalogDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long consentId;

    @NotBlank(message = "Consent type is required")
    @Size(max = 50, message = "Consent type must not exceed 50 characters")
    private String consentType;

    @NotBlank(message = "Consent description is required")
    @Size(max = 500, message = "Consent description must not exceed 500 characters")
    private String consentDescription;

    private Integer expiryPeriodDays;

    @Size(max = 20, message = "Consent version must not exceed 20 characters")
    private String consentVersion;

    @Size(max = 100, message = "Consent source must not exceed 100 characters")
    private String consentSource;

    @NotNull(message = "Status is required")
    private StatusEnum status;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}