package sia.tacocloud.messager.rabbitmq.sender.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.messager.MessageSender;

public interface RabbitMqTacoOrderMessageSenderService extends MessageSender<TacoOrder> {
}
