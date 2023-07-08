package sia.tacocloud.services.message.rabbitmq.receiver.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.message.MessageReceiver;

public interface RabbitMqTacoOrderMessageReceiverService extends MessageReceiver<TacoOrder> {
}
