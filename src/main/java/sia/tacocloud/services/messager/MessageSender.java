package sia.tacocloud.services.message;

public interface MessageSender <T> {
    void sendMessage(T message);
}
