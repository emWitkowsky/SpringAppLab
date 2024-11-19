package com.example.spring.lab.spring.lab.controller;

import com.example.spring.lab.spring.lab.domain.Person;
import com.example.spring.lab.spring.lab.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
//        Optional<Person> person = personService.getPersonById(id);
////        if (person == null) {
////            return "redirect:/persons/list";
////        }
//        model.addAttribute("person", person);
//        return "persons/edit";
        Optional<Person> personOptional = personService.getPersonById(id);
        if (personOptional.isPresent()) {
            model.addAttribute("person", personOptional.get());
            return "persons/edit";  // Zwraca widok formularza edycji
        } else {
            model.addAttribute("errorMessage", "Podana osoba nie istnieje.");
            return "persons/error";
        }
    }

    @PostMapping("/edit/{id}")
    public String saveEditedPerson(@PathVariable("id") long id, @Valid @ModelAttribute("person") Person person) {
        person.setId(id);
        personService.updatePerson(id, person);
        return "redirect:/persons/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return "redirect:/persons/list";
    }

    @ModelAttribute("countries")
    public List<String> getCountries() {
        return List.of("Polska", "Niemcy", "Francja", "Wielka Brytania", "USA");
    }

}
