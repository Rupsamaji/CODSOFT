package com.rupsa.jobs.controller;

import com.rupsa.jobs.service.JobService.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> notFound(JobNotFoundException ex){return Map.of("error",ex.getMessage());}

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> invalid(MethodArgumentNotValidException ex){
        String message=ex.getBindingResult().getFieldErrors().stream()
          .map(e->e.getField()+": "+e.getDefaultMessage()).findFirst().orElse("Invalid request");
        return Map.of("error",message);
    }
}