package sia.tacocloud.services;

import org.springframework.data.jpa.domain.Specification;
import sia.tacocloud.entities.TacoOrder;

import java.util.Date;

public class TacoOrderSpecifications {

    public static Specification<TacoOrder>hasDeliveryCity(String deliveryCity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deliveryCity"), deliveryCity);
    }

    public static Specification<TacoOrder>hasDeliveryStreet(String deliveryStreet) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deliveryStreet"), deliveryStreet);
    }

    public static Specification<TacoOrder>hasPlacedAtBetween(Date startDate, Date endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("placedAt"), startDate, endDate);
    }

}
