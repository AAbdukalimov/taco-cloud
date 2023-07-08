package sia.tacocloud.services.messager;

public interface MessageSender <T> {
    void sendMessage(T message);
}
