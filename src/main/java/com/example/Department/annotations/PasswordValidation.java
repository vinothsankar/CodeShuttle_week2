package com.example.Department.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface PasswordValidation {

    String message() default "Roll of employee user or admin";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int min() default 8;
    int max() default 20;
    boolean specialCharacterRequired() default true;

}