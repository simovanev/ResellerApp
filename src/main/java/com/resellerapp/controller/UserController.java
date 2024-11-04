package com.resellerapp.controller;

import com.resellerapp.model.dtos.LoginDto;
import com.resellerapp.model.dtos.RegisterDto;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

    @ModelAttribute("registerDto")
    public RegisterDto newRegister() {
        return new RegisterDto();
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto
            , BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (userService.isDuplicateEmail(registerDto)) {
            FieldError emailError = new FieldError(
                    "registerDto", "email", "Email is already in use");
            bindingResult.addError(emailError);
        }
        if (!userService.goodCredentials(registerDto)) {
            FieldError confirmPasswordError = new FieldError(
                    "registerDto", "confirmPassword", "Passwords must be the same");
            bindingResult.addError(confirmPasswordError);
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerDto", registerDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.registerDto", bindingResult);
            return "redirect:register";
        }
        userService.saveUser(registerDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid LoginDto loginDto, BindingResult bindingResult, RedirectAttributes
            redirectAttributes) {
        if (!userService.isRegistered(loginDto)) {
            FieldError noRegistrationError = new FieldError(
                    "loginDto", "noRegistrationError", "Incorrect username or password");
            bindingResult.addError(noRegistrationError);
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDto", loginDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.loginDto", bindingResult);
            return "redirect:/login";
        }
        userService.login(loginDto);
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}
