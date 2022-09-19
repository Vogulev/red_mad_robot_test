package com.red_mad_robot.backend_test.model;

import com.red_mad_robot.backend_test.HasId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Getter
@Setter
@ToString
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity implements HasId {
    public static final int START_SEQ = 100000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (isNull(o) || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return nonNull(id) && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return isNull(id) ? 0 : id;
    }
}