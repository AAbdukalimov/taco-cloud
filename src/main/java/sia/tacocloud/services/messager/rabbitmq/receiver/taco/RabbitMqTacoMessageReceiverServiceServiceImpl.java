package sia.tacocloud.services.message.rabbitmq.receiver.taco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;

@Slf4j
@Service
public class RabbitMqTacoMessageReceiverServiceServiceImpl implements RabbitMqTacoMessageReceiverService {


    @RabbitListener(queues = "tacocloud.taco.exchange")
    @Override
    public void receiveMessage(Taco message) {
        log.debug("Received from Taco RabbitMQ: " + message);
        // your logic here
    }

}
