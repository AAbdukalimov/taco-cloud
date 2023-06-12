package sia.tacocloud.services.tacoorder;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;
import sia.tacocloud.repositories.TacoOrderRepository;

import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
public class TacoOrderServiceImpl implements TacoOrderService{

    private TacoOrderRepository tacoOrderRepository;

    @Autowired
    public TacoOrderServiceImpl(TacoOrderRepository tacoOrderRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        return tacoOrderRepository.save(tacoOrder);
    }

    @Override
    public List<TacoOrder> findByDeliveryZip(String deliveryZip) {
        return tacoOrderRepository.findByDeliveryZip(deliveryZip);
    }

    @Override
    public List<TacoOrder> findOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate) {
        return tacoOrderRepository.findOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip, startDate, endDate);
    }

    @Override
    public List<TacoOrder> findAll(Specification<TacoOrder> specification) {
        return tacoOrderRepository.findAll(specification);
    }

    @Override
    public List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable) {
        return tacoOrderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

}
