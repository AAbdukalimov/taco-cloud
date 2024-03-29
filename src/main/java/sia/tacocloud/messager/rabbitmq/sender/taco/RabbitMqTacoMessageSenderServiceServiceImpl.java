package sia.tacocloud.messager.rabbitmq.sender.taco;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMqTacoMessageSenderServiceServiceImpl implements RabbitMqTacoMessageSenderService {

    private final RabbitTemplate rabbit;

    @Override
    public void sendMessage(Taco message) {
        rabbit.convertAndSend("tacocloud.taco.exchange", "kitchens.central", message);
        log.debug("Taco sent to RabbitMQ: " + message);
    }

}
