package com.mytests.spring.webflux.firstwebfluxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstWebfluxTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstWebfluxTestApplication.class, args);
        PersonWebClient webClient = new PersonWebClient();
        webClient.consume();
    }

}
