package sia.tacocloud.services.messager.kafka.sender.tacoorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.TacoOrder;

@Service
public class KafkaTacoOrderMessagingSenderServiceImpl implements KafkaTacoOrderMessagingSenderService {
    private final KafkaTemplate<String, TacoOrder> kafkaTemplate;

    @Autowired
    public KafkaTacoOrderMessagingSenderServiceImpl(KafkaTemplate<String, TacoOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(TacoOrder message) {
        kafkaTemplate.send("tacocloud.orders.topic", message);
    }

}
