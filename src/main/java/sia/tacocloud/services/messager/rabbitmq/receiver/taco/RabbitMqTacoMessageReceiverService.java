package sia.tacocloud.services.messager.rabbitmq.receiver.taco;

import sia.tacocloud.entities.Taco;
import sia.tacocloud.services.messager.MessageReceiver;

public interface RabbitMqTacoMessageReceiverService extends MessageReceiver<Taco> {
}
