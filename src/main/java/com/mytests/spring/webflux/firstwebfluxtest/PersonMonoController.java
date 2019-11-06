package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/28/2019.
 * Project: webflux-webclient-annotations-test
 * *******************************
 */
@RestController
public class PersonMonoController {
    @Autowired
    PersonRepo personRepo;

    @GetMapping("/persons/mono/{id}")
    public Mono<Person> getById(@PathVariable String id) {

        return personRepo.getPersonById(Integer.parseInt(id));
    }

    @GetMapping("persons/mono")
    public Mono<Person> getByIdAsParam(@RequestParam("idparam") String id) {
        return personRepo.getPersonById(Integer.parseInt(id));
    }
}
