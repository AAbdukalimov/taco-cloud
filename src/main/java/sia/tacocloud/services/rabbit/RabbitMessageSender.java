package sia.tacocloud.services.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.TacoOrder;

@Slf4j
@Service
public class RabbitMessageSender implements MessageSender {
    private final RabbitTemplate rabbit;

    @Autowired
    public RabbitMessageSender(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    @Override
    public void sendMessage(TacoOrder orderMessage) {
//        MessageConverter converter = rabbit.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        Message message = converter.toMessage(orderMessage, props);
//        rabbit.send("tacocloud.order", message);
        rabbit.convertAndSend("tacocloud.order", "kitchens.central", orderMessage);
        log.debug("Order sent to RabbitMQ: " + orderMessage);
    }

//    @Override
//    public void sendMessage(String message) {
//        rabbit.convertAndSend("tacocloud.order", "kitchens.central", message);
//        System.out.println("Сообщение отправлено: " + message);
//    }

}
