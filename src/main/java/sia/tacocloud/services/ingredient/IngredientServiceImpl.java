package sia.tacocloud.services.ingredient;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Ingredient;
import sia.tacocloud.repositories.IngredientRepository;

import java.util.List;

@Service
@NoArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository repo;

    @Autowired
    public IngredientServiceImpl(IngredientRepository repo) {
        this.repo = repo;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @Override
    public List<Ingredient> findAll() {
       return (List<Ingredient>) repo.findAll();
    }

    @Override
    public void deleteById(String id) {
         repo.deleteById(id);
    }
}
