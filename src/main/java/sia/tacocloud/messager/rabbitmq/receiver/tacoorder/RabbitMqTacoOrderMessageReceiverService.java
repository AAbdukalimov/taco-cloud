package sia.tacocloud.messager.rabbitmq.receiver.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.messager.MessageReceiver;

public interface RabbitMqTacoOrderMessageReceiverService extends MessageReceiver<TacoOrder> {
}
