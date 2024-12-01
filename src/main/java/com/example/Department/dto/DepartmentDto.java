package com.example.Department.dto;

import com.example.Department.annotations.PasswordValidation;
import com.example.Department.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {

    private long id;

    @Size(min=3, message = "No of character should be > 3")
    private String name;

    @Min(value = 18, message = "Age should be 18-45")
    @Max(value = 45, message = "Age should be 18-45")
    private int age;

    @Email(message = "Enter the valid Email")
    private String email;

    @PrimeNumberValidation()
    private int primNumber;

    @CreditCardNumber(message = "Enter a valid Credit card number")
    private long creteCard;

    @AssertTrue
    private boolean isActive;

    @JsonFormat(pattern = "dd-MM-YYYY hh:mm:ss")
    private LocalDate createdAt;

    @PasswordValidation()
    private String password;
}
