package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonController {

     @Autowired
     PersonRepo personRepo;

    @GetMapping("/flux/")
    public Flux<Person> getAll() {
        return  personRepo.getAllPersons();

    }


    @GetMapping("/flux/names/{name}")
    public Flux<Person> getByName(Model model, @PathVariable String name) {
        return personRepo.getPersonsByName(name) ;
    }
    @GetMapping("/flux/ages/{age}")
    public Flux<Person> getByAge(Model model, @PathVariable(value = "age") String age) {
        return personRepo.getPersonsByAge(Integer.parseInt(age));
    }
}
