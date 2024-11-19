package com.example.spring.lab.spring.lab.controller;


import com.example.spring.lab.spring.lab.domain.Person;
import com.example.spring.lab.spring.lab.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

//    public List<Person> getAllPersons() {
//        return person.findAll();
//    }
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable long id) {
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}")
    public Optional<Person> updatePersonById(@PathVariable long id, @RequestBody Person updatedPerson){
        return personService.updatePerson(id, updatedPerson);
    }

    @DeleteMapping("/{id}")
    public boolean deletePersonById(@PathVariable long id) {
        return personService.deletePersonById(id);
    }

    @PostMapping("/newPerson")
    public boolean createNewPerson(@RequestBody Person createdPerson) {
        return personService.createNewPerson(createdPerson);
    }
}
