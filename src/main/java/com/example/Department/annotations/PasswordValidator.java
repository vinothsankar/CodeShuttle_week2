package com.example.Department.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, String> {

    private int min;
    private int max;
    private boolean specialCharacterRequired;

    @Override
    public void initialize(PasswordValidation constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
        this.specialCharacterRequired = constraintAnnotation.specialCharacterRequired();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()){
            return false;
        }
        if(password.length() > max || password.length() < min){
            return false;
        }
        if(specialCharacterRequired && !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")){
            return false;
        }
        return true;
    }
}
