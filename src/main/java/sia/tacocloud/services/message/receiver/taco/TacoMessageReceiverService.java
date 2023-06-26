package sia.tacocloud.services.message.receiver.taco;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;

@Slf4j
@Service
public class TacoMessageReceiverService implements TacoMessageReceiver {


    @RabbitListener(queues = "tacocloud.taco.exchange")
    @Override
    public void receiveMessage(Taco message) {
        log.debug("Received from Taco RabbitMQ: " + message);
        // your logic here
    }

}
