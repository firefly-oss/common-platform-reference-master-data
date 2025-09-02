package com.firefly.masters.models.entities.contracttype.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("contract_type")
@Getter
@Setter
@NoArgsConstructor
public class ContractType {

    @Id
    @Column("contract_id")
    private UUID contractId;

    @Column("contract_code")
    private String contractCode;

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