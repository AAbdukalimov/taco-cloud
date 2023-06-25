package sia.tacocloud.services.message.sender;

public interface MessageSender <T> {

    void sendMessage(T message);
//    void sendMessage(String message);
}
