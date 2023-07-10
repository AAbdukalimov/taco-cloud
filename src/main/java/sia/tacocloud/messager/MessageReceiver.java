package sia.tacocloud.messager;


public interface MessageReceiver <T> {

void receiveMessage(T message);

}
