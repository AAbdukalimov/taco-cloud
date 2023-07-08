package sia.tacocloud.services.messager.rabbitmq.sender.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.messager.MessageSender;

public interface RabbitMqTacoOrderMessageSenderService extends MessageSender<TacoOrder> {
}
