package sia.tacocloud.services.tacoorder;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;

import java.util.Date;
import java.util.List;

public interface TacoOrderService {

    TacoOrder save(TacoOrder tacoOrder);
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> findOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<TacoOrder>findAll(Specification<TacoOrder> specification);
    List<TacoOrder> findByUserOrderByPlacedAtDesc (User user, Pageable pageable);
}
