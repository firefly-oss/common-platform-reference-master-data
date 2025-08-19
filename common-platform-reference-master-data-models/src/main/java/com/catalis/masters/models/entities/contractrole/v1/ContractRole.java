package com.catalis.masters.models.entities.contractrole.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("contract_role")
@Getter
@Setter
@NoArgsConstructor
public class ContractRole {

    @Id
    @Column("role_id")
    private Long roleId;

    @Column("role_code")
    private String roleCode;

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
