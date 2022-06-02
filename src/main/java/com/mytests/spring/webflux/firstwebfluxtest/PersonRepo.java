package com.mytests.spring.webflux.firstwebfluxtest;

import com.mytests.spring.webflux.firstwebfluxtest.data.Person;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * *******************************
 * Created by Irina.Petrovskaya on 10/21/2019.
 * Project: webflux-webclient-annotations-test
 * *******************************
 */
@Repository
public class PersonRepo {

    static List<Person> personList ;
    static {
        personList = new ArrayList<>();
        personList.add(new Person("irina",1, 49));
        personList.add(new Person("vera", 2, 23));
        personList.add(new Person("andrey", 3, 49));
        personList.add(new Person("lena", 4, 49));
        personList.add(new Person("katya", 5, 26));
        personList.add(new Person("natasha", 6, 72));
        personList.add(new Person("andrey", 7, 74));
    }

    public Flux<Person> getAllPersons() {
        return Flux.fromIterable(personList);
    }

    public Mono<Person> getFirstPerson() {
        Person rez = personList.get(0);
        return Mono.just(rez);
    }

    public Mono<Person> getPersonById(Integer id) {
        Person rez = new Person("not found", 0, 0);
        for (Person person : personList) {
            if (person.getId().equals(id)) {
                rez = person;
            }
        }
        return Mono.just(rez);
    }

    public Flux<Person> getPersonsByName(String name) {
        List<Person> rez = new ArrayList<>();
        for (Person person : personList) {
            if (person.getName().equals(name)){rez.add(person);}
        }
        return Flux.fromIterable(rez);
    }
    public Flux<Person> getPersonsByAge(int age){
        List<Person> rez = new ArrayList<>();
        for (Person person : personList) {
            if (person.getAge()>= age){rez.add(person);}
        }
        return Flux.fromIterable(rez);
    }

    public Person addPerson(Person person){
        personList.add(person);
        return person;
    }
}
