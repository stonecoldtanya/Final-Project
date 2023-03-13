package com.example.checkers.checkers.controllers;

import com.example.checkers.checkers.bussiness.services.ContestantService;
import com.example.checkers.checkers.session.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class HomeController {
    private final LoggedUser userSession;
    private final ContestantService userService;

    public HomeController(LoggedUser userSession, ContestantService userService) {
        this.userSession = userSession;
        this.userService = userService;
    }

    @GetMapping("/home")
    private String getHome(Model model) {
        if (!userSession.isLogged()) {
            return "/index";
        }
        return "/home";
    }

    @PostMapping("home")
    private String home(Model model){
        if (!userSession.isLogged()) {
            return "/index";
        }
        return "/home";
    }

}
