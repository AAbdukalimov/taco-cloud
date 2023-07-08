package sia.tacocloud.services.message.rabbitmq.sender.tacoorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.TacoOrder;

@Slf4j
@Service
public class RabbitMqTacoOrderMessageSenderServiceServiceImpl implements RabbitMqTacoOrderMessageSenderService {
    private final RabbitTemplate rabbit;

    @Autowired
    public RabbitMqTacoOrderMessageSenderServiceServiceImpl(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendMessage(TacoOrder message) {
//        MessageConverter converter = rabbit.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        Message message = converter.toMessage(message, props);
//        rabbit.send("tacocloud.order", message);
        rabbit.convertAndSend("tacocloud.order.exchange", "kitchens.central", message);
        log.debug("Order sent to RabbitMQ: " + message);
    }

}
