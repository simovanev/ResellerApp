package com.resellerapp.controller;

import com.resellerapp.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Controller
public class HomeController {
    private CurrentUser currentUser;

    @GetMapping("/home")
    public String home() {
        if (currentUser.getId() == 0) {
            return "redirect:/";
        }
        return "home";
    }
}
