package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/22/2019.
 * Project: webflux-webclient-annotations-test
 * *******************************
 */
public class PersonWebClient {
    WebClient client = WebClient.create("http://localhost:8080/mywebfluxapp");
    public void consume(){
        System.out.println("===== test web client =====");
        Mono<Person> personById = client.get().uri("/persons/mono/{id}", "1").retrieve().bodyToMono(Person.class);
        personById.subscribe(x -> System.out.println("mono: person by id==1 " + x.getName()));
        Flux<Person> all = client.get().uri("/persons/flux/").retrieve().bodyToFlux(Person.class);
        all.subscribe(x -> System.out.println("flux: all: " + x.getName()));
        Flux<Person> byName =
                client.get().uri("/persons/flux/names/{name}", "andrey").retrieve().bodyToFlux(Person.class);
        byName.subscribe(x -> System.out.println("flux: persons by name == andrey: " + x.getName()));
        Flux<Person> byAge = client.get().uri("/persons/flux/ages/50")
                .retrieve().bodyToFlux(Person.class)
                .filter(person ->
                        (person.getId()>0) )
                .map(person -> {
                    System.out.println("**********************************");
                    System.out.println(person.getName());
                    System.out.println("**********************************");
                    return person;
                });
        byAge.subscribe(x -> System.out.println("flux: persons by age > 50: " + x.getName()));
        Mono<Person> paramTestPerson = client.get().uri("/persons/mono?idparam=2").retrieve().bodyToMono(Person.class);
        paramTestPerson.subscribe(x -> System.out.println("req parameters test :" + x.getName()));
        Mono<Person> paramTestPerson2 = client.get()
                .uri(uriBuilder -> uriBuilder.path("/persons/mono/")
                        .queryParam("idparam", "3").build())

                .retrieve()
                .bodyToMono(Person.class);
        paramTestPerson2.subscribe(x -> System.out.println("req parameters test2 :" + x.getName()));
    }
    
}
 
