package sia.tacocloud.services.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Role;
import sia.tacocloud.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
