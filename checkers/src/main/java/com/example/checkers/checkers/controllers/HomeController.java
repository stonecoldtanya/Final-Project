package com.example.checkers.checkers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/home")
public class HomeController {
    public HomeController() {
    }

    @RequestMapping("/home")
    @GetMapping(" ")
    public @ResponseBody String greeting(@RequestParam(name = "name", required = false, defaultValue = "Gamer") String name) {
        return String.format("Hello, %s!", name);
    }
}
