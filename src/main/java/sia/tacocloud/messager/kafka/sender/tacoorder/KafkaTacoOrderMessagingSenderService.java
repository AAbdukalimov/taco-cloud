package sia.tacocloud.messager.kafka.sender.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.messager.MessageSender;

public interface KafkaTacoOrderMessagingSenderService extends MessageSender<TacoOrder> {

}
