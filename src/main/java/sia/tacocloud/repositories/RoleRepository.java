package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);
}
