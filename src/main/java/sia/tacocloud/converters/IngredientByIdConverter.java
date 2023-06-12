package sia.tacocloud.converters;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.entities.Ingredient;
import sia.tacocloud.repositories.IngredientRepository;


@Component
@NoArgsConstructor
public class IngredientByIdConverter implements Converter<String, Ingredient> {



    //FIRST VERSION

//    private Map<String, Ingredient>ingredientMap = new HashMap<>();
//
//    public IngredientByIdConverter() {
//        ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//        ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//        ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//        ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//        ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//        ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//        ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
//        ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//        ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
//        ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//    }
//
//    @Override
//    public Ingredient convert(String id) {
//        return ingredientMap.get(id);
//    }

    // SECOND VERSION

    private IngredientRepository ingredientRepository;


    @Autowired
    public IngredientByIdConverter (IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
