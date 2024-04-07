package com.dpd.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EntityBase other = (EntityBase) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public int compareTo(EntityBase o) {
        if (o == null) {
            return 1;
        }
        if (!this.getClass().equals(o.getClass())) {
            return 1;
        }
        return Long.compare(this.getId(), o.getId());
    }

}
