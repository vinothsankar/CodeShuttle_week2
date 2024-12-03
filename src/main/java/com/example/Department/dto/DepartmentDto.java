package com.example.Department.dto;

import com.example.Department.annotations.PasswordValidation;
import com.example.Department.annotations.PrimeNumberValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String creteCard;

    @JsonProperty("isActive")
    @AssertTrue(message = "isActive must be true")
    private Boolean isActive;


    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime updatedAt;

    @PasswordValidation(min = 6, max = 10, specialCharacterRequired = true, message = "Password must include uppercase, lowercase, digit, special character, and be between 6 and 10 characters")
    private String password;

}
