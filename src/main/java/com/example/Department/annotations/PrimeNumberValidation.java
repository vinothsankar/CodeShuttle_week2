package com.example.Department.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PrimeNumberValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface PrimeNumberValidation {

    String message() default "Number should be Prime Number";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] Payload() default { };

 }
