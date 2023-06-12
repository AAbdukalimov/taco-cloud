package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.entities.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
