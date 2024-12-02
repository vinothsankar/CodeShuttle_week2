package com.example.Department.annotations;

import ch.qos.logback.core.net.SyslogOutputStream;
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

        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{" + min + "," + max + "}$";

        if (password == null || password.isBlank()){
            context.disableDefaultConstraintViolation(); // for disable default error message
            context.buildConstraintViolationWithTemplate("password cannot be null or blank").addConstraintViolation(); // set your own message
            return false;
        }
        if(password.length() > max || password.length() < min){
            return false;
        }                    ;
        try { // for any mistaken in pattern variable
            if (specialCharacterRequired && !password.matches(pattern)) {
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
