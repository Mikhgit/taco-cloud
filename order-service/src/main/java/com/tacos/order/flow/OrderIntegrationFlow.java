package com.tacos.order.flow;

import com.tacos.order.controller.mapper.OrderDtoMapper;
import com.tacos.order.domain.OrderEntity;
import com.tacos.order.dto.OrderDto;
import com.tacos.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderIntegrationFlow {

    public static final String LOG_CATEGORY = "com.tacos.order.flow";
    public static final String NEW_ORDER = "NEW ORDER FROM RABBIT: %s";

    @Bean
    public IntegrationFlow orderCreationFlow() {
        return IntegrationFlows
                .from(MessageConsumer.class, (gateway) -> gateway.beanName("orderCreateRequest"))
                .convert(OrderDto.class)
                .transform(orderEntityTransformer(null))
                .handle(saveOrder(null))
                .log(LoggingHandler.Level.INFO, LOG_CATEGORY, message -> String.format(NEW_ORDER, message.getPayload()))
                .get();
    }

    @Bean
    public GenericHandler<OrderEntity> saveOrder(OrderRepository repository) {
        return (payload, headers) -> repository.save(payload);
    }

    @Bean
    public GenericTransformer<OrderDto, OrderEntity> orderEntityTransformer(OrderDtoMapper mapper) {
        return mapper::fromDto;
    }

    @ServiceActivator(inputChannel = "errorChannel")
    void handleLeadFlowErrors(Message<MessagingException> message) {
        log.error("Error creating order: {}", message);
    }

    private interface MessageConsumer extends Consumer<Message<?>> {
    }
}