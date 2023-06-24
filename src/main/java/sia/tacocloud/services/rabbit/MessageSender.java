package sia.tacocloud.services.rabbit;

import sia.tacocloud.entities.TacoOrder;

public interface MessageSender {

    void sendMessage(TacoOrder orderMessage);
//    void sendMessage(String message);
}
