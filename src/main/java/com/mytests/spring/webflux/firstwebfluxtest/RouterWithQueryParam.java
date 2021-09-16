package com.mytests.spring.webflux.firstwebfluxtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

// https://youtrack.jetbrains.com/issue/IDEA-234930 - request parameters are not supported
@Configuration
public class RouterWithQueryParam {

    @Bean
    public RouterFunction<ServerResponse> routeSingleQueryParams(){

        return route(method(HttpMethod.GET).and(path("/test_router/with_reqparam_one")
                        .and(queryParam("my_param0", t->true))),
                this::process00);
    }

    @Bean
    public RouterFunction<ServerResponse> routeTwoQueryParams(){

        return route(method(HttpMethod.GET).and(path("/test_router/with_reqparam_two")
                                           .and(queryParam("my_param1", t -> true))
                                           .and(queryParam("my_param2", t -> true))),
                this::process01);
    }

    private <T extends ServerResponse> Mono<ServerResponse> process00(ServerRequest req) {
        return ServerResponse.ok().body(Mono.just(req.queryParam("my_param0").get()), String.class);
    }
    private <T extends ServerResponse> Mono<ServerResponse> process01(ServerRequest req) {
        return ServerResponse.ok().body(Mono.just(req.queryParam("my_param1").get().concat(req.queryParam("my_param2").get())), String.class);
    }
}
