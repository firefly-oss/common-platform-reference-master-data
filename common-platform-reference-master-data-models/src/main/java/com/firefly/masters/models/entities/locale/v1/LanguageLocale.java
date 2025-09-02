package com.firefly.masters.models.entities.locale.v1;

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
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("language_locale")
public class LanguageLocale {

    @Id
    @Column("locale_id")
    private UUID localeId;

    @Column("language_code")
    private String languageCode;

    @Column("country_code")
    private String countryCode;

    @Column("locale_code")
    private String localeCode;

    @Column("language_name")
    private String languageName;

    @Column("native_name")
    private String nativeName;

    @Column("region_name")
    private String regionName;

    @Column("rtl")
    private Boolean rtl;

    @Column("sort_order")
    private Integer sortOrder;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
