package sia.tacocloud.restful;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.entities.Taco;
import sia.tacocloud.services.taco.TacoService;


@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoRestController {

    private final TacoService tacoService;

    @Autowired
    public TacoRestController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping("/findAll")
    public ResponseEntity<Taco> findAll() {
        if (tacoService.findAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body((Taco) tacoService.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
        if (tacoService.findById(id) != null) {
            return ResponseEntity.ok(tacoService.findById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco save(@RequestBody Taco taco) {
        return tacoService.save(taco);
    }


    @DeleteMapping("/{tacoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("tacoId") Long tacoId) {
        try {
            tacoService.deleteById(tacoId);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }

}
