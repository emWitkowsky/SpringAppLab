package com.example.spring.lab.spring.lab.repository;

import com.example.spring.lab.spring.lab.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PeopleRepository {

    private final List<Person> people = new ArrayList<>();

    public PeopleRepository() {
        initializePeople();
    }

    private void initializePeople() {
        people.add(new Person(1, "John", "Doe", "john.doe@example.com", "Company A", 2000, "EUR", "Polska"));
        people.add(new Person(2, "Jane", "Smith", "jane.smith@example.com", "Company B", 2000, "EUR", "Polska"));
        people.add(new Person(3, "Alice", "Johnson", "alice.johnson@example.com", "Company C", 2000, "JPY", "Polska"));
        people.add(new Person(4, "Bob", "Brown", "bob.brown@example.com", "Company D", 2000, "EUR", "Niemcy"));
    }

    public List<Person> findAll() {
        return new ArrayList<>(people);
    }

    public Optional<Person> findById(Long id) {
        return people.stream().filter(person -> person.getId() == id).findFirst();
    }

    public void save(Person person) {
        people.add(person);
    }

    public void deleteById(Long id) {
        people.removeIf(person -> person.getId() == id);
    }
}
