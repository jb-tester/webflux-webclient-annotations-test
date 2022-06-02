package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
//import static reactor.core.publisher.Mono.when;

@RunWith(SpringRunner.class)
@WebFluxTest(PersonMonoController.class)
class FirstWebfluxTestApplicationTests {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private PersonRepo personRepo;

    @Test
    public void testGetEmployeeById() {
        Person p = new Person("test", 99, 99);
        Mono<Person> employeeMono = Mono.just(p);

        when(personRepo.getPersonById(99)).thenReturn(employeeMono);

        webTestClient.get()
                .uri("/persons/mono/{id}", 99)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("{\n" +
                        "  \"name\": \"test\",\n" +
                        "  \"id\": 99,\n" +
                        "  \"age\": 99\n" +
                        "}");
    }

    @Test
    public void testGetEmployeeByIdParam() {
        Person p = new Person("test_param", 999, 999);
        Mono<Person> employeeMono = Mono.just(p);

        when(personRepo.getPersonById(999)).thenReturn(employeeMono);

        webTestClient.get()
                .uri("/persons/mono?idparam={id}", 999)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .value(employee1 -> p.getAge(), equalTo(999));
    }

    @Test
    public void testPersonAdding() {
       webTestClient.post()
               .uri("/add")
               .contentType(MediaType.APPLICATION_JSON)
               .bodyValue("{\n" +
                       "  \"id\": 100,\n" +
                       "  \"age\": 26,\n" +
                       "  \"name\": \"Leva\"\n" +
                       "}")
               .exchange()
               .expectStatus().isOk();

    }
}
