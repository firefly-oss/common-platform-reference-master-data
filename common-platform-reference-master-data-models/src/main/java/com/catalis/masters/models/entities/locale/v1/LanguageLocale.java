package com.catalis.masters.models.entities.locale.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("language_locale")
public class LanguageLocale {

    @Id
    @Column("id")
    private Long id;

    @Column("language_code")
    private String languageCode;

    @Column("locale_code")
    private String localeCode;

    @Column("language_name")
    private String languageName;

    @Column("region_name")
    private String regionName;

    @Column("status")
    private StatusEnum status;
}
