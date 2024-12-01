package com.example.Department.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Integer> {
    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext context) {
        if(number == null) return false;
        return isPrime(number);
    }

    private boolean isPrime(Integer n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) { // Check up to the square root of n
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
