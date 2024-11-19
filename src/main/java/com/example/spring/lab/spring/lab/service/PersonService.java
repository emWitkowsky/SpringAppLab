package com.example.spring.lab.spring.lab.service;

//import org.example.domain.PersonCSV;
//import org.example.spring.lab.spring.lab.domain.Person;

import com.example.spring.lab.spring.lab.domain.Person;
//import com.example.spring.lab.spring.lab.domain.PersonCSV;
import com.example.spring.lab.spring.lab.repository.PeopleRepository;
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
    private final PeopleRepository peopleRepository;

    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

//    public PersonService() {
//        initializePeople();
//    }
//
//    private void initializePeople() {
//        people.add(new Person(1, "John", "Doe", "john.doe@example.com", "Company A", 2000, "EUR", "Polska"));
//        people.add(new Person(2, "Jane", "Smith", "jane.smith@example.com", "Company B", 2000, "EUR", "Polska"));
//        people.add(new Person(3, "Alice", "Johnson", "alice.johnson@example.com", "Company C", 2000, "JPY", "Polska"));
//        people.add(new Person(4, "Bob", "Brown", "bob.brown@example.com", "Company D", 2000, "EUR", "Niemcy"));
//    }

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
//        return new ArrayList<>(people);
        return peopleRepository.findAll();
    }

    public Optional<Person> getPersonById(long id) {
//        return people.stream().filter(person -> person.getId() == id).findFirst();
        return peopleRepository.findById(id);
    }

    public Optional<Person> updatePerson(long id, Person updatedPerson) {
        return getPersonById(id).map(person -> {
            person.setName(updatedPerson.getName());
            person.setLastName(updatedPerson.getLastName());
            person.setEmail(updatedPerson.getEmail());
            person.setCompany(updatedPerson.getCompany());
            person.setSalary(updatedPerson.getSalary());
            person.setCurrency(updatedPerson.getCurrency());
            person.setCountry(updatedPerson.getCountry());
            return person;
        });
    }

    public void deletePersonById(long id) {
//        return people.stream().filter(person -> person.getId() != id );
//        return people.removeIf(person -> person.getId() == id);
        peopleRepository.deleteById(id);
    }

    public void createNewPerson(Person createdPerson) {
        peopleRepository.save(new Person((int) createdPerson.getId(),
                createdPerson.getName(),
                createdPerson.getLastName(),
                createdPerson.getEmail(),
                createdPerson.getCompany(),
                createdPerson.getSalary(),
                createdPerson.getCurrency(),
                createdPerson.getCountry()));
    }

}