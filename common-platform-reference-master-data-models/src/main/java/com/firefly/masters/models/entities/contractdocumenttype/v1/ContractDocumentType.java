package com.firefly.masters.models.entities.contractdocumenttype.v1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("contract_document_type")
@Getter
@Setter
@NoArgsConstructor
public class ContractDocumentType {

    @Id
    @Column("document_type_id")
    private UUID documentTypeId;

    @Column("document_code")
    private String documentCode;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("is_active")
    private Boolean isActive;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
