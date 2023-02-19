package com.tacos.taco.controller;

import com.tacos.taco.config.SecurityConfig;
import com.tacos.taco.controller.mapper.TacoDtoMapperImpl;
import com.tacos.taco.domain.TacoEntity;
import com.tacos.taco.dto.TacoDto;
import com.tacos.taco.service.NewTacoForm;
import com.tacos.taco.service.TacoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(DesignTacoController.class)
@Import({TacoDtoMapperImpl.class, SecurityConfig.class})
class DesignTacoControllerTest {

    @Autowired
    private WebTestClient testClient;
    @MockBean
    private TacoService tacoService;

    @Test
    @WithMockUser
    public void shouldReturnRecentTacos() {
        when(tacoService.findRecentTacos()).thenReturn(Flux.just(getTacoEntity()));

        testClient.get().uri("/tacos/recent")
                .accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[?(@.id == 'TACO_ID')].name")
                .isEqualTo("TACO");
    }

    @Test
    @WithMockUser
    public void shouldSaveATaco() {
        NewTacoForm form = NewTacoForm.builder()
                .ingredients(List.of("ingredient"))
                .name("TACO")
                .build();
        when(tacoService.create(form)).thenReturn(Mono.just(getTacoEntity()));

        testClient.post()
                .uri("/tacos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(form)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TacoDto.class);
    }

    @Test
    @WithMockUser
    public void shouldReturnTacoById() {
        when(tacoService.getById("TACO_ID")).thenReturn(Mono.just(getTacoEntity()));

        testClient.get().uri("/tacos/TACO_ID")
                .accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[?(@.id == 'TACO_ID')].name")
                .isEqualTo("TACO");
    }

    @Test
    public void shouldDenyRequestTacoById() {
        testClient.get().uri("/tacos/TACO_ID")
                .accept(MediaType.APPLICATION_JSON).exchange()
                .expectStatus().isUnauthorized();
    }

    private TacoEntity getTacoEntity() {
        TacoEntity taco = new TacoEntity("TACO", List.of("ingredient"));
        taco.setId("TACO_ID");
        return taco;
    }
}