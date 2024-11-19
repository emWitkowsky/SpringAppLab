package com.example.spring.lab.spring.lab.controller;

import com.example.spring.lab.spring.lab.domain.Person;
import com.example.spring.lab.spring.lab.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonViewController {

    private final PersonService personService;

    @Autowired
    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping("/list")
    public String listPersons(Model model) {
//        model.addAttribute("persons", personService.getAllPersons());
        List<Person> people = personService.getAllPersons();

        // Dodajemy osoby do modelu
        model.addAttribute("persons", people);

        // Dodajemy licznik osób do modelu
        model.addAttribute("personCount", people.size());
        return "persons/list";
    }

    @GetMapping("/details/{id}")
    public String personDetails(@PathVariable long id, Model model) {
//        model.addAttribute("person", personService.getPersonById(id));
        if (personService.getPersonById(id) == null) {
            model.addAttribute("errorMessage", "Podana osoba nie istnieje.");
            return "persons/error";
        }
        personService.getPersonById(id).ifPresent(person -> model.addAttribute("person", person));
        return "persons/details";
    }
}
