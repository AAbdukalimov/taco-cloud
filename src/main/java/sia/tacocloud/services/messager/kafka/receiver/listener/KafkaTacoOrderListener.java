package sia.tacocloud.services.messager.kafka.receiver.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.messager.kafka.receiver.KitchenUI;

@Component
public class KafkaTacoOrderListener {

    private final KitchenUI ui;

    @Autowired
    public KafkaTacoOrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    @KafkaListener(topics="tacocloud.orders.topic", groupId = "tacocloud-group-id")
    public void handle(TacoOrder order, Model model) {
        ui.displayOrder(order.getId(), model);
    }

}
