package sia.tacocloud.messager;

public interface MessageSender <T> {
    void sendMessage(T message);
}
