package tacos.web.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Order;
import tacos.data.OrderRepository;
import tacos.messaging.OrderMessagingService;

@RestController
@RequestMapping(path = "/orders",
        produces = "application/json")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository repo;
    private final OrderMessagingService orderMessages;

    @GetMapping(produces = "application/json")
    public Flux<Order> allOrders() {
        return repo.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> postOrder(@RequestBody Order order) {
        orderMessages.sendOrder(order);
        return repo.save(order);
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Mono<Order> putOrder(@RequestBody Order order) {
        return repo.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Mono<Order> patchOrder(@PathVariable("orderId") String orderId,
                                  @RequestBody Order patch) {

        return repo.findById(orderId)
                .map(order -> {
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
                        order.setDeliveryZip(patch.getDeliveryState());
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
                    return order;
                })
                .flatMap(repo::save);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") String orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
