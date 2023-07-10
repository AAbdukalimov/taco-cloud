package sia.tacocloud.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.entities.TacoOrder;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession session;

    @GetMapping
    public String home(Model model) {
        TacoOrder tacoOrder = (TacoOrder) session.getAttribute("order");
        model.addAttribute("tacoOrder", tacoOrder);
        log.debug("Order: " + tacoOrder);
        return "home";
    }

}
