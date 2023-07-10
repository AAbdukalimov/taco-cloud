package sia.tacocloud.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.configs.OrderProperties;
import sia.tacocloud.configs.integrationconfig.JavaStyleFileWriterIntegrationConfig;
import sia.tacocloud.configs.security.UserDetailsServiceImpl;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;
import sia.tacocloud.integration.FileWriterGateway;
import sia.tacocloud.messager.kafka.receiver.KitchenUI;
import sia.tacocloud.messager.kafka.sender.tacoorder.KafkaTacoOrderMessagingSenderService;
import sia.tacocloud.messager.rabbitmq.receiver.taco.RabbitMqTacoMessageReceiverService;
import sia.tacocloud.messager.rabbitmq.receiver.tacoorder.RabbitMqTacoOrderMessageReceiverService;
import sia.tacocloud.messager.rabbitmq.sender.taco.RabbitMqTacoMessageSenderService;
import sia.tacocloud.messager.rabbitmq.sender.tacoorder.RabbitMqTacoOrderMessageSenderService;
import sia.tacocloud.services.taco.TacoService;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    private final RabbitMqTacoOrderMessageSenderService rabbitMqTacoOrdermessageSenderService;
    private final RabbitMqTacoMessageSenderService rabbitMqTacoMessageSenderService;
    private final RabbitMqTacoOrderMessageReceiverService rabbitMqTacoOrderMessageReceiverService;
    private final RabbitMqTacoMessageReceiverService rabbitMqTacoMessageReceiverService;
    private final KafkaTacoOrderMessagingSenderService kafkaTacoOrderMessagingSenderService;
    private final KitchenUI kitchenUI;
    private final JavaStyleFileWriterIntegrationConfig javaStyleFileWriterIntegrationConfig;
    private final ConfigurableApplicationContext context;

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
        log.debug("TacoOrder: " + order);
        session.setAttribute("order", order);

        tacos.forEach(taco -> taco.setTacoOrder(order));
        tacoService.saveTacoList(tacos);

        rabbitMqTacoOrdermessageSenderService.sendMessage(order);
        tacos.forEach(rabbitMqTacoMessageSenderService::sendMessage);

        rabbitMqTacoOrderMessageReceiverService.receiveMessage(order);
        tacos.forEach(rabbitMqTacoMessageReceiverService::receiveMessage);


        log.debug("tacos from tacoOrder: " + tacoOrder.getTacos());

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProperties.getPageSize());
        model.addAttribute("orders", tacoOrderService.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/{orderId}")
    public void displayOrder(@PathVariable("orderId") Long orderId, Model model) {
        log.debug("Order ID: " + orderId);
        TacoOrder order = kitchenUI.displayOrder(orderId);

        model.addAttribute("order", order);

        log.debug("Order: " + session.getAttribute("order"));

        String filename = order.getId().toString() + "-" + order.getDeliveryName() + "-" + "datetime" + order.getPlacedAt().toString() + ".txt";
        String data = order.toString();
        log.debug("Data: " + data);

        context.getBean(FileWriterGateway.class).writeToFile(filename, data);
        log.debug("FileWriterGateway interface writeToFile() method is active");

        GenericTransformer<String, String> stringStringGenericTransformer = javaStyleFileWriterIntegrationConfig.upperCaseTransformer();
        log.debug("GenericTransformer: " + stringStringGenericTransformer);

        FileWritingMessageHandler fileWritingMessageHandler = javaStyleFileWriterIntegrationConfig.fileWriter();
        log.debug("FileWritingMessageHandler: " + fileWritingMessageHandler.getBeanDescription());

    }

}

