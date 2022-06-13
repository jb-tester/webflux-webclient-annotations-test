package com.mytests.spring.webflux.firstwebfluxtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FirstWebfluxTestApplication {

    public static void main(String[] args) {
        //Hooks.onOperatorDebug();
        SpringApplication.run(FirstWebfluxTestApplication.class, args);
        PersonWebClient webClient = new PersonWebClient();
        webClient.consume();
    }

}
