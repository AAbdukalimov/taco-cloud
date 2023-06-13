package sia.tacocloud.services.ingredient;

import sia.tacocloud.entities.Ingredient;

import java.util.List;

public interface IngredientService {

    Ingredient save(Ingredient ingredient);
    List<Ingredient> findAll();
    void deleteById(String id);
}
