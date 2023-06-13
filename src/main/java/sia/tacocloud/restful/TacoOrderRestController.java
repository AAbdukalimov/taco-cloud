package sia.tacocloud.restful;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entities.TacoOrder;
import sia.tacocloud.services.tacoorder.TacoOrderService;

import java.util.List;


@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoOrderRestController {

    private final TacoOrderService tacoOrderService;

    public TacoOrderRestController(TacoOrderService tacoOrderService) {
        this.tacoOrderService = tacoOrderService;
    }

    @PostMapping(name = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        return tacoOrderService.save(order);
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
        }
        catch (EmptyResultDataAccessException ignored) {
        }
    }

}
