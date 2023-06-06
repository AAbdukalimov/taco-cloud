package sia.tacocloud.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.entities.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder>findByDeliveryZip(String deliveryZip);
    List<TacoOrder>readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<TacoOrder>findAll(Specification<TacoOrder>specification);
}
