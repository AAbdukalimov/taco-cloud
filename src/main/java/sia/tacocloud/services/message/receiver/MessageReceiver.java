package sia.tacocloud.services.message.receiver;


public interface MessageReceiver <T> {

void receiveMessage(T message);

}
