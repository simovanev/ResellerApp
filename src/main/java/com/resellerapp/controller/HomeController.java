package com.resellerapp.controller;

import com.resellerapp.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;

@Controller
public class HomeController {
    private CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/home")
    public String home( Model model) {
        if (currentUser.getId() == 0) {
            return "redirect:/";
        }

        model.addAttribute("userName", currentUser);





        return "home";
    }
}
