package sia.tacocloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "taco")
@NoArgsConstructor
@AllArgsConstructor
public class Taco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message="You must choose at least 1 ingredient")
    @OneToMany
    @ToString.Exclude
    private List<Ingredient> ingredients;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn (name = "taco_order_id")
    @ToString.Exclude
    private TacoOrder tacoOrder;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Taco taco = (Taco) o;
        return id != null && Objects.equals(id, taco.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
