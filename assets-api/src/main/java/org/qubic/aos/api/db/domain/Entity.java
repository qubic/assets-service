package org.qubic.aos.api.db.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Data
@Table("entities")
public class Entity {

    @Id
    private Long id;
    private String identity;

}

