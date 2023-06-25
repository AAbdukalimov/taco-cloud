package sia.tacocloud.controllers;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.services.taco.TacoService;

import java.util.List;

@Slf4j
@Builder
@Controller
@RequestMapping("/tacos")
@RequiredArgsConstructor
public class TacoController {

    private final TacoService tacoService;


    @GetMapping()
    public String showTacosPage(Model model, Pageable pageable) {
        Page<Taco> tacoPage = tacoService.findAll(pageable);
        model.addAttribute("tacoPage", tacoPage);

        model.addAttribute("allTacos", tacoPage.getContent());
        model.addAttribute("currentPage", tacoPage.getNumber());
        model.addAttribute("totalPages", tacoPage.getTotalPages());

        return "tacoPage";
    }

}
