package sia.tacocloud.controllers;


import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entities.Ingredient;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.enums.Type;
import sia.tacocloud.repositories.IngredientRepository;
import sia.tacocloud.services.taco.TacoService;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@Builder
@RequestMapping("/design")
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoService tacoService;
    private final TacoOrderService tacoOrderService;
    private final HttpSession session;
    private final List<Taco> tacos;


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
    @Transactional
    public String processTaco(@Valid Taco tacoSession, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        session.setAttribute("tacoSession", tacoSession);

        tacos.add(tacoSession);
        session.setAttribute("tacos", tacos);

        session.setAttribute("tacoOrder", tacoOrder);
        tacoOrder.setTacos(tacos);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, String type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
