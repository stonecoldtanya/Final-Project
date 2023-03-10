package com.example.checkers.checkers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
//@EnableAutoConfiguration
@RequestMapping("/home")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "welcome";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String redirect() {
        return "redirect:/";
    }

}
