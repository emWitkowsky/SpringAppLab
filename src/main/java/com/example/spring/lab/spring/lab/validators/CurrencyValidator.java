package com.example.spring.lab.spring.lab.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    private static final Set<String> ALLOWED_CURRENCIES = Set.of("EUR", "USD", "GBP", "JPY");

    public void initialize(ValidCurrency constraint) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ALLOWED_CURRENCIES.contains(value);
    }
}
