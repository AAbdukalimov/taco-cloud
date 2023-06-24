package sia.tacocloud.rest;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.rabbit.MessageSender;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import java.util.List;


@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoOrderRestController {

    private final TacoOrderService tacoOrderService;
    private final MessageSender messageSender;

    public TacoOrderRestController(TacoOrderService tacoOrderService, MessageSender messageSender) {
        this.tacoOrderService = tacoOrderService;
        this.messageSender = messageSender;
    }

    @PostMapping(name = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public TacoOrder postOrder(@RequestBody TacoOrder tacoOrder) {
        messageSender.sendMessage(tacoOrder);
        return tacoOrderService.save(tacoOrder);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TacoOrder>> findAll() {
        if (tacoOrderService.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(tacoOrderService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> findById(@PathVariable("id") Long id) {
        if (tacoOrderService.findById(id) != null) {
            return ResponseEntity.ok(tacoOrderService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder order) {
        order.setId(orderId);
        return tacoOrderService.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
        TacoOrder order = tacoOrderService.findById(orderId);
        return tacoOrderService.patchUpdate(order, patch);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            tacoOrderService.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

}
