package sia.tacocloud.services.messager;


public interface MessageReceiver <T> {

void receiveMessage(T message);

}
