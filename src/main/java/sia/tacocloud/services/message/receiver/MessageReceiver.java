package sia.tacocloud.services.message.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.entities.TacoOrder;

@Component
public class MessageReceiver {



    @RabbitListener(queues = "tacocloud.taco.exchange")
    public void receiveMessageFromTaco(Taco message) {
        System.out.println("Received message from Taco: " + message);
        // Ваша логика обработки полученного сообщения здесь

    }

}
