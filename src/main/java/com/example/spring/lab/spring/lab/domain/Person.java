package com.example.spring.lab.spring.lab.domain;

import com.example.spring.lab.spring.lab.validators.ValidCurrency;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {


    private long id;

    @NotBlank
    @Size(max=50, min=2, message = "Musi byc miedzy 2 a 50")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "Imię może zawierać tylko litery i myślniki.")
    private String name;

    @NotBlank
    @Size(min = 2,max = 50, message = "Musi byc miedzy 2 a 50")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "Nazwisko może zawierać tylko litery i myślniki.")
    private String lastName;

    @NotBlank(message = "Email nie moze byc pusty")
    @Email(message = "Email ma miec składnie emaila")
    private String email;
    private String company;

    @DecimalMin(value = "0.01", message = "Wynagrodzenie musi być dodatnie.")
    @DecimalMax(value = "1000000.00", message = "Wynagrodzenie nie może być większe niż 1,000,000.")
    @Digits(integer = 7, fraction = 2, message = "Wynagrodzenie musi być liczbą z maksymalnie 2 miejscami po przecinku.")
    private double salary;

    @ValidCurrency
    private String currency;
    private String country;

    public Person() {
    }

    public Person(int id, String name, String lastName, String email, String company, double salary, String currency, String country) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.salary = salary;
        this.currency = currency;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName
    ) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email
    ) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}
