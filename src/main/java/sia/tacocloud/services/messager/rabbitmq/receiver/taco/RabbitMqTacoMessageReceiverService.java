package sia.tacocloud.services.message.rabbitmq.receiver.taco;

import sia.tacocloud.entities.Taco;
import sia.tacocloud.services.message.MessageReceiver;

public interface RabbitMqTacoMessageReceiverService extends MessageReceiver<Taco> {
}
