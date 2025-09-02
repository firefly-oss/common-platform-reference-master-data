package com.firefly.masters.models.entities.relationships.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("relationship_type_master")
@Getter
@Setter
@NoArgsConstructor
public class RelationshipTypeMaster {

    @Id
    @Column("relationship_type_id")
    private UUID relationshipTypeId;

    @Column("relationship_type_code")
    private String code; // e.g. "BENEFICIARY", "CEO"

    @Column("relationship_type_description")
    private String description; // e.g. "Beneficiary", "Chief Executive Officer"

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}
