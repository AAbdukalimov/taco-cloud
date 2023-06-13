package sia.tacocloud.services.taco;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.repositories.TacoRepository;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public List<Taco> findAll() {
        return (List<Taco>) tacoRepository.findAll();
    }

    @Override
    public Taco findById(Long id) {
    return tacoRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("No such taco found"));
    }

    @Override
    public void deleteById(Long id) {
        tacoRepository.deleteById(id);
    }
}
