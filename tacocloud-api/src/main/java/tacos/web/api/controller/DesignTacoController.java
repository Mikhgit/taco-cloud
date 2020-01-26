package tacos.web.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design",                      // <1>
        produces = "application/json")
@CrossOrigin(origins = "*")        // <2>
@RequiredArgsConstructor
public class DesignTacoController {
    private final TacoRepository tacoRepo;

    @GetMapping("/recent")
    public Flux<Taco> recentTacos() {                 //<3>
        return tacoRepo.findAll().take(12);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Taco> postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") String id) {
        return tacoRepo.findById(id);
    }
}

