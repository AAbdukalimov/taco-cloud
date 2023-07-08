package sia.tacocloud.services.messager.kafka.sender.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.messager.MessageSender;

public interface KafkaTacoOrderMessagingSenderService extends MessageSender<TacoOrder> {

}
