package com.catalis.masters.interfaces.dtos.locale.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageLocaleDTO {

    private Long localeId;
    private String languageCode;
    private String countryCode;
    private String localeCode;
    private String languageName;
    private String nativeName;
    private String regionName;
    private Boolean rtl;
    private Integer sortOrder;
    private StatusEnum status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
