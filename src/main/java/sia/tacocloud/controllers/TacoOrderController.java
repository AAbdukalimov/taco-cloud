package sia.tacocloud.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.configs.OrderProperties;
import sia.tacocloud.configs.security.UserDetailsServiceImpl;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;
import sia.tacocloud.services.rabbit.MessageSender;
import sia.tacocloud.services.taco.TacoService;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Data
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class TacoOrderController {

    private final TacoOrderService tacoOrderService;
    private final OrderProperties orderProperties;
    private final UserDetailsServiceImpl userDetailsService;
    private final HttpSession session;
    private final List<Taco> tacos;
    private final TacoService tacoService;
    private final MessageSender messageSender;

//    @Autowired
//    public TacoOrderController
//            (
//                    TacoOrderService tacoOrderService,
//                    OrderProperties orderProperties,
//                    UserDetailsServiceImpl userDetailsService,
//                    HttpSession session,
//                    List<Taco> tacos,
//                    TacoService tacoService
//            ) {
//        this.tacoOrderService = tacoOrderService;
//        this.orderProperties = orderProperties;
//        this.userDetailsService = userDetailsService;
//        this.session = session;
//        this.tacos = tacos;
//        this.tacoService = tacoService;
//    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping("/create")
    @Transactional
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        User user = (User) session.getAttribute("user");

        Taco tacoSession = (Taco) session.getAttribute("tacoSession");

        tacos.add(tacoSession);

        tacoOrder.setTacos(tacos);
        tacoOrder.setUser(user);
        TacoOrder order = tacoOrderService.save(tacoOrder);
//        messageSender.sendMessage(order);

        tacoSession.setTacoOrder(order);
        Taco taco = tacoService.save(tacoSession);

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("orders", tacoOrderService.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

}

