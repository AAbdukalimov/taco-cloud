package sia.tacocloud.messager.kafka.sender.taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.Taco;

@Service
public class KafkaTacoMessagingSenderServiceImpl implements KafkaTacoMessagingSenderService {

    private final KafkaTemplate<String, Taco> kafkaTemplate;

    @Autowired
    public KafkaTacoMessagingSenderServiceImpl(KafkaTemplate<String, Taco> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(Taco message) {
        kafkaTemplate.send("tacocloud.tacos.topic", message);
    }

}
