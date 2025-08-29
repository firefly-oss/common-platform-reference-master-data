package com.firefly.masters.models.entities.title.v1;

import com.firefly.masters.interfaces.enums.commons.v1.StatusEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("title_master")
@Getter
@Setter
@NoArgsConstructor
public class TitleMaster {

    @Id
    @Column("title_id")
    private Long titleId;

    @Column("title_prefix")
    private String prefix; // e.g. "MR", "MRS", "DR"

    @Column("title_description")
    private String description; // e.g. "Mr.", "Mrs.", "Doctor"

    @Column("status")
    private StatusEnum status;

    @Column("date_created")
    private LocalDateTime dateCreated;

    @Column("date_updated")
    private LocalDateTime dateUpdated;
}