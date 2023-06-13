package sia.tacocloud.services.taco;

import sia.tacocloud.entities.Taco;

import java.util.List;

public interface TacoService {

    Taco save(Taco taco);
    List<Taco> findAll();
    Taco findById(Long id);
    void deleteById(Long id);
}
