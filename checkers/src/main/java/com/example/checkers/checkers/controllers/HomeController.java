package com.example.checkers.checkers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@EnableAutoConfiguration
//@RequestMapping("/home")
public class HomeController {

//    @RequestMapping("/home")
    @GetMapping("/home")
    @ResponseBody
    public String greeting() {
        return  "Hello, gamer";
    }
}
