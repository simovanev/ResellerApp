package com.resellerapp.controller;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @ModelAttribute("loginDto")
    public LoginDto initLoginDto() {
        return new LoginDto();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
       // model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid LoginDto loginDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto",loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto",bindingResult);
            return "login";
        }
        if (!userService.isLogged(loginDto)) {
            redirectAttributes.addFlashAttribute("loginDto",loginDto);
            redirectAttributes.addFlashAttribute("incorrectCredentials",true);
            return "redirect:/login";
        }

        return "redirect:/home";
    }
}
