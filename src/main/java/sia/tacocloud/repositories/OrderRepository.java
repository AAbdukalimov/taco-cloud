package sia.tacocloud.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.entities.TacoOrder;

import java.util.Date;
import java.util.List;


public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder>findByDeliveryZip(String deliveryZip);
    List<TacoOrder> findOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
    List<TacoOrder>findAll(Specification<TacoOrder>specification);

}
