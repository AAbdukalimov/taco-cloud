package sia.tacocloud.messager.kafka.receiver;


import sia.tacocloud.entities.TacoOrder;

public interface KitchenUI {

   TacoOrder displayOrder(Long orderId);

}
