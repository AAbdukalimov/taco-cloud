//package sia.tacocloud.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import sia.tacocloud.entities.TacoOrder;
//import sia.tacocloud.services.messager.MessageSender;
//
//@RestController
//public class RabbitRestController {
//    private final MessageSender messageSender;
//
//    @Autowired
//    public RabbitRestController(MessageSender messageSender) {
//        this.messageSender = messageSender;
//    }
//
//    @GetMapping("/send-message")
//    public String sendMessage(TacoOrder tacoOrder) {
//        messageSender.sendMessage(tacoOrder);
//        return "Сообщение отправлено";
//    }
//
//}
