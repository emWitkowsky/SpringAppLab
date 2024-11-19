package com.example.spring.lab.spring.lab.service;

//import org.example.domain.PersonCSV;
//import org.example.spring.lab.spring.lab.domain.Person;

import com.example.spring.lab.spring.lab.domain.Person;
//import com.example.spring.lab.spring.lab.domain.PersonCSV;
import org.springframework.stereotype.Service;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
//import java.util.stream.Collectors;

@Service
public class PersonService {

private final List<Person> people = new ArrayList<>();

//    public PeopleService() {};

    public PersonService() {
        initializePeople();
    }

    private void initializePeople() {
        people.add(new Person(1, "John", "Doe", "john.doe@example.com", "Company A"));
        people.add(new Person(2, "Jane", "Smith", "jane.smith@example.com", "Company B"));
        people.add(new Person(3, "Alice", "Johnson", "alice.johnson@example.com", "Company C"));
        people.add(new Person(4, "Bob", "Brown", "bob.brown@example.com", "Company D"));
    }

//    public List<Person> loadPeopleFromCSV(String filePath) {
//        List<Person> people = new ArrayList<>();
//        String line;
//        try (BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(filePath).getFile()))) {
//            br.readLine(); // Skip header line
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                if (values.length >= 3) {
//                    String firstName = values[0];
//                    String lastName = values[1];
//                    String email = values[2];
//                    String salary = values[3];
//                    String currency = values[4];
//                    String country = values[5];
//                    people.add(new PersonCSV(firstName, lastName, email, salary, currency, country));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return people;
//    }

//    public static List<Person> sortByLastName(List<Person> persons) {
//        return persons.stream()
//                .map(person -> (PersonCSV) person)
//                .sorted(Comparator.comparing(Person::getLastName))
//                .collect(Collectors.toList());
//    }
//
//    public static List<PersonCSV> filterByCountry(List<Person> persons, String country) {
//        return persons.stream()
//                .map(person -> (PersonCSV) person)
//                .filter(person -> person.getCountry().equalsIgnoreCase(country))
//                .collect(Collectors.toList());
//    }

    public List<Person> getAllPersons() {
        return new ArrayList<>(people);
    }

    public Optional<Person> getPersonById(long id) {
        return people.stream().filter(person -> person.getId() == id).findFirst();
    }

    public Optional<Person> updatePerson(long id, Person updatedPerson) {
        return getPersonById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setLastName(updatedPerson.getLastName());
            person.setEmail(updatedPerson.getEmail());
            person.setCompany(updatedPerson.getCompany());
            return person;
        });
    }

    public boolean deletePersonById(long id) {
//        return people.stream().filter(person -> person.getId() != id );
        return people.removeIf(person -> person.getId() == id);
    }

    public boolean createNewPerson(Person createdPerson) {
        return people.add(new Person(createdPerson.getId(),
                createdPerson.getName(),
                createdPerson.getLastName(),
                createdPerson.getEmail(),
                createdPerson.getCompany()));
    }

}