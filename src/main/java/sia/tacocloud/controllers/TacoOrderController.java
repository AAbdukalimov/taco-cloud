package sia.tacocloud.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import sia.tacocloud.services.message.receiver.taco.TacoMessageReceiver;
import sia.tacocloud.services.message.receiver.tacoorder.TacoOrderMessageReceiver;
import sia.tacocloud.services.message.sender.taco.TacoMessageSender;
import sia.tacocloud.services.message.sender.tacoorder.TacoOrderMessageSender;
import sia.tacocloud.services.taco.TacoService;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Data
@Slf4j
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class TacoOrderController {

    private final TacoOrderService tacoOrderService;
    private final OrderProperties orderProperties;
    private final UserDetailsServiceImpl userDetailsService;
    private final HttpSession session;
    private final TacoService tacoService;
    private final TacoOrderMessageSender tacoOrdermessageSender;
    private final TacoMessageSender tacoMessageSender;
    private final TacoOrderMessageReceiver tacoOrderMessageReceiver;
    private final TacoMessageReceiver tacoMessageReceiver;
    private List<Taco> tacos;


    @GetMapping("/current")
    public String orderForm(Model model) {
        TacoOrder tacoOrder = (TacoOrder) session.getAttribute("tacoOrder");
        model.addAttribute("tacoOrder", tacoOrder);
        return "orderForm";
    }

    @PostMapping("/create")
    @Transactional
    public String processOrder(@ModelAttribute @Valid TacoOrder tacoOrder, Errors errors) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        User user = (User) session.getAttribute("user");

        tacos = (List<Taco>) session.getAttribute("tacos");
        log.debug("tacos from session: " + tacos);

        tacoOrder.setTacos(tacos);
        tacoOrder.setUser(user);
        TacoOrder order = tacoOrderService.save(tacoOrder);

        tacos.forEach(taco -> taco.setTacoOrder(order));
        tacoService.saveTacoList(tacos);

        tacoOrdermessageSender.sendMessage(order);
        tacos.forEach(tacoMessageSender::sendMessage);

        tacoOrderMessageReceiver.receiveMessage(order);
        tacos.forEach(tacoMessageReceiver::receiveMessage);

        log.debug("tacos from tacoOrder: " + tacoOrder.getTacos());

        return "redirect:/";
    }


    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("orders", tacoOrderService.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

}

