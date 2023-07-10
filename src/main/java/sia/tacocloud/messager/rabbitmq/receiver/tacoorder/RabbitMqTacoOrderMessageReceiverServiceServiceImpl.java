package sia.tacocloud.messager.rabbitmq.receiver.tacoorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.TacoOrder;

@Slf4j
@Service
public class RabbitMqTacoOrderMessageReceiverServiceServiceImpl implements RabbitMqTacoOrderMessageReceiverService {

    @RabbitListener(queues = "tacocloud.order.exchange")
    @Override
    public void receiveMessage(TacoOrder message) {
        log.debug("Received from TacoOrder RabbitMQ: " + message);
        // your logic here
    }

}
