package com.example.Department.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.List;

@Builder
@Data
public class ApiError {
    private String message;
    private HttpStatus status;
    private List<String> subErrors;
}
