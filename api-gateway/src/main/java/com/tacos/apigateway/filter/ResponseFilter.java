package com.tacos.apigateway.filter;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ResponseFilter {

    public static final String TRACE_ID = "trace_id";
    private final Tracer tracer;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    String traceId = Optional.ofNullable(tracer.currentSpan())
                            .map(Span::context)
                            .map(TraceContext::traceIdString)
                            .orElse(null);
                    if (traceId == null) {
                        log.debug("Trace id is not present.");
                        return;
                    }
                    log.debug("Adding the trace id to the outbound headers. {}",traceId);
                    exchange.getResponse().getHeaders().add(TRACE_ID,traceId);
                    log.debug("Completing outgoing request for {}.", exchange.getRequest().getURI());
                }));
    }
}
