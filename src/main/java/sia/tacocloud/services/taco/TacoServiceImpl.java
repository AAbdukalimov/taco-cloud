package sia.tacocloud.services.taco;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.repositories.TacoRepository;

@Service
@NoArgsConstructor
public class TacoServiceImpl implements TacoService {

    private TacoRepository tacoRepository;

    @Autowired
    public TacoServiceImpl(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }
    @Override
    public Taco save(Taco taco) {
        return tacoRepository.save(taco);
    }

}
