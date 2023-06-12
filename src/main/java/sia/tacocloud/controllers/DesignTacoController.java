package sia.tacocloud.controllers;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacocloud.entities.Ingredient;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.enums.Type;
import sia.tacocloud.repositories.IngredientRepository;
import sia.tacocloud.services.taco.TacoService;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Builder
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private IngredientRepository ingredientRepository;
    private TacoService tacoService;
    private TacoOrderService tacoOrderService;
    private HttpSession session;


    @Autowired
    public DesignTacoController
            (
                    IngredientRepository ingredientRepository,
                    TacoService tacoService,
                    TacoOrderService tacoOrderService,
                    HttpSession session
            ) {
        this.ingredientRepository = ingredientRepository;
        this.tacoService = tacoService;
        this.tacoOrderService = tacoOrderService;
        this.session = session;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        Type[] types = Type.values();

        for (Type type : types) {
            model.addAttribute(type.getTypeName().toLowerCase(), filterByType(ingredients, type.getTypeName()));
        }

    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping("/page")
    public String showDesignForm() {
        return "design";
    }

    @PostMapping("/create")
    public String processTaco(@Valid Taco tacoSession, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        session.setAttribute("tacoSession", tacoSession);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
