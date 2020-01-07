package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import tacos.Order;

@Service
@RequiredArgsConstructor
public class RabbitOrderMessagingService
        implements OrderMessagingService {

    private final RabbitTemplate rabbit;

    public void sendOrder(Order order) {
        rabbit.convertAndSend("amq.fanout", "", order, message -> {
            MessageProperties props = message.getMessageProperties();
            props.setHeader("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }

}
