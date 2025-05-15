package com.catalis.masters.models.entities.transaction.v1;

import com.catalis.masters.interfaces.enums.commons.v1.StatusEnum;
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
 * Entity representing a localized version of a transaction category.
 * This stores translations of transaction category information for different languages/locales.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("transaction_category_localization")
public class TransactionCategoryLocalization {

    @Id
    @Column("localization_id")
    private Long localizationId;

    @Column("category_id")
    private Long categoryId;  // References transaction_category_catalog(category_id)

    @Column("locale_id")
    private Long localeId;  // References language_locale(locale_id)

    @Column("category_name")
    private String categoryName;

    @Column("description")
    private String description;

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
