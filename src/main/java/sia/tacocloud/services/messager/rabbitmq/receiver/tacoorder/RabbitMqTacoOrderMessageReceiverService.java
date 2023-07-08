package sia.tacocloud.services.messager.rabbitmq.receiver.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.messager.MessageReceiver;

public interface RabbitMqTacoOrderMessageReceiverService extends MessageReceiver<TacoOrder> {
}
