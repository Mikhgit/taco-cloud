package tacos.web.api.controller;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;
import tacos.data.TacoRepository;
import tacos.web.api.resource.TacoResource;
import tacos.web.api.resource.TacoResourceAssembler;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacosController {

    private TacoRepository tacoRepo;

    public RecentTacosController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public Mono<ResponseEntity<Resources<TacoResource>>> recentTacos() {
        return tacoRepo.findAll()
                .take(12)
                .collectList()
                .map(tacos -> {
                    List<TacoResource> tacoResources =
                            new TacoResourceAssembler().toResources(tacos);
                    Resources<TacoResource> recentResources =
                            new Resources<TacoResource>(tacoResources);

                    recentResources.add(
                            linkTo(methodOn(RecentTacosController.class).recentTacos())
                                    .withRel("recents"));
                    return new ResponseEntity<>(recentResources, HttpStatus.OK);
                });
    }

}
