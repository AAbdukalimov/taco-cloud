package sia.tacocloud.controllers;


//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;
import sia.tacocloud.repositories.OrderRepository;
import javax.validation.Valid;


@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm.html";
    }

//    @PostMapping
//    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
//        if (errors.hasErrors()) {
//            return "orderForm.html";
//        }
//
//        order.setUser(user);
//        orderRepository.save(order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }

}

