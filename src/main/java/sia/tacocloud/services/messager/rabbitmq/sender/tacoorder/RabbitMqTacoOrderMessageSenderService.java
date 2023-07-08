package sia.tacocloud.services.message.rabbitmq.sender.tacoorder;

import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.message.MessageSender;

public interface RabbitMqTacoOrderMessageSenderService extends MessageSender<TacoOrder> {
}
