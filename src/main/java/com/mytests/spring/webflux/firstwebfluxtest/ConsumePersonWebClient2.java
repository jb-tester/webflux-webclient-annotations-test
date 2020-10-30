package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * *
 * <p>Created by irina on 30.10.2020.</p>
 * <p>Project: webflux-webclient-annotations-test</p>
 * *
 */
@RestController
public class ConsumePersonWebClient2 {

    @Autowired
    private PersonWebClient2 personWebClient2;

    @GetMapping("/test_webcontroller_service/")
    public Mono<Person> test_webcontroller_service() {
        return personWebClient2.getFirstPersonFromWebClientService();

    }
}
