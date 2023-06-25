package sia.tacocloud.services.taco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sia.tacocloud.entities.Taco;

import java.util.List;

public interface TacoService {

    Taco save(Taco taco);
    List<Taco> findAll();
    Page<Taco> findAll(Pageable pageable);
    Taco findById(Long id);
    void deleteById(Long id);

}
