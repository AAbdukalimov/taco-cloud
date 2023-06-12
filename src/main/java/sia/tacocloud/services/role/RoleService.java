package sia.tacocloud.services.role;

import sia.tacocloud.entities.Role;

public interface RoleService {
    Role findByName(String name);
}
