package com.example.crudmavenapp.config;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundError(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorMessage", "The page you are looking for does not exist.");
        return "error"; // error.html page
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralError(Exception ex, Model model) {
        model.addAttribute("errorMessage", "An unexpected error has occurred.");
        return "error"; // error.html page
    }
}
