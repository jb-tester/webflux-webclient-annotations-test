package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonWebClient2 {

    private final WebClient webClient;

    public PersonWebClient2(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build(); // https://youtrack.jetbrains.com/issue/IDEA-254250
    }

    public Mono<Person> getFirstPersonFromWebClientService() {
        return this.webClient.get().uri("/persons/mono_first")
                .retrieve().bodyToMono(Person.class);
    }

}