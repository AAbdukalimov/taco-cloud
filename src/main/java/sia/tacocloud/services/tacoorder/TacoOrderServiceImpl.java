package sia.tacocloud.services.tacoorder;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.entities.User;
import sia.tacocloud.repositories.TacoOrderRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@NoArgsConstructor
public class TacoOrderServiceImpl implements TacoOrderService {

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
    public List<TacoOrder> findAll() {
        return (List<TacoOrder>) tacoOrderRepository.findAll();
    }

    @Override
    public List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable) {
        return tacoOrderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @Override
    public TacoOrder findById(Long orderId) {
        return tacoOrderRepository.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("No such order found"));
    }

    @Override
    public void deleteById(Long id) {
        tacoOrderRepository.deleteById(id);
    }

    @Override
    public TacoOrder patchUpdate(TacoOrder order, TacoOrder patch) {
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return tacoOrderRepository.save(order);
    }
}
