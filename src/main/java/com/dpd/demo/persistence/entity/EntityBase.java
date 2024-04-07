package com.dpd.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class EntityBase implements Comparable<EntityBase> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Builder.Default
    private Long version = 1L;

    @Override
    public int compareTo(EntityBase o) {
        if (o == null) {
            return 1;
        }
        return Long.compare(this.getId(), o.getId());
    }

}
