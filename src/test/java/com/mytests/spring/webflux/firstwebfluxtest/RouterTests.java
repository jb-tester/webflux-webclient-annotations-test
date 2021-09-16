package com.mytests.spring.webflux.firstwebfluxtest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(RouterWithQueryParam.class)
class RouterTests {
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testRouterWithSingleQueryParamSet(){
        webTestClient.get()
                .uri("/test_router/with_reqparam_one?my_param0=foo")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("foo");
    }
    @Test
    public void testRouterWithTwoQueryParamsSet(){
        webTestClient.get()
                .uri("/test_router/with_reqparam_two?my_param1=foo&my_param2=bar")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("foobar");
    }
    @Test
    public void testRouterWithQueryParamNotSet(){
        webTestClient.get()
                .uri("/test_router/with_reqparam_one")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
