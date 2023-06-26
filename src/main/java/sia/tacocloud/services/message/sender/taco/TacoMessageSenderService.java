package sia.tacocloud.services.message.sender.taco;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;

@Slf4j
@Service
@RequiredArgsConstructor
public class TacoMessageSenderService implements TacoMessageSender {

    private final RabbitTemplate rabbit;

    @Override
    public void sendMessage(Taco message) {
        rabbit.convertAndSend("tacocloud.taco.exchange", "kitchens.central", message);
        log.debug("Taco sent to RabbitMQ: " + message);
    }

}
