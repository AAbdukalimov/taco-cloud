package sia.tacocloud.services.message;


public interface MessageReceiver <T> {

void receiveMessage(T message);

}
