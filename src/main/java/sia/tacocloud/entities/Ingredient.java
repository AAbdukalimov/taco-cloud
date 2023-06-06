package sia.tacocloud.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Persistable;
import sia.tacocloud.entities.enums.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;


@Getter
@Setter
@ToString
//@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ingredient that = (Ingredient) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
