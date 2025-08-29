package com.firefly.masters.models.entities.assettype.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("asset_type")
@Getter
@Setter
@NoArgsConstructor
public class AssetType {

    @Id
    @Column("asset_id")
    private Long assetId;

    @Column("asset_code")
    private String assetCode;

    @Column("description")
    private String description;

    @Column("name")
    private String name;

    @Column("is_active")
    private Boolean isActive;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}