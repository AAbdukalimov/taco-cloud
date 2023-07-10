package sia.tacocloud.messager.kafka.receiver.listener;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.messager.kafka.receiver.KitchenUI;

@Component
@RequiredArgsConstructor
public class KafkaTacoOrderListener {

    private final KitchenUI ui;

    @KafkaListener(topics="tacocloud.orders.topic", groupId = "tacocloud-group-id")
    public void handle(TacoOrder order) {
        ui.displayOrder(order.getId());
    }

}
