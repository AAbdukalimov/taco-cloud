package sia.tacocloud.messager.kafka.receiver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.tacoorder.TacoOrderService;

@Service
@RequiredArgsConstructor
public class KitchenUIImpl implements KitchenUI {

    private final TacoOrderService tacoOrderService;
    @Override
    public TacoOrder displayOrder(Long orderId) {
        return tacoOrderService.findById(orderId);
    }

}
