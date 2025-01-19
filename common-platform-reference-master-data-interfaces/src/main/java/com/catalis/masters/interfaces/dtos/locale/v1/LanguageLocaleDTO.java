package com.catalis.masters.interfaces.dtos.locale.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageLocaleDTO {

    private Long id;
    private String languageCode;
    private String localeCode;
    private String languageName;
    private String regionName;
    private StatusEnum status;
}