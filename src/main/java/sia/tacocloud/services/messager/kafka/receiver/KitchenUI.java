package sia.tacocloud.services.messager.kafka.receiver;


import org.springframework.ui.Model;

public interface KitchenUI {

   String displayOrder(Long orderId, Model model);

}
