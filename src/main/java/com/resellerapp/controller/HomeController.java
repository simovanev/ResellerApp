package com.resellerapp.controller;

import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.session.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private CurrentUser currentUser;
    private OfferService offerService;

    public HomeController(CurrentUser currentUser, OfferService offerService) {
        this.currentUser = currentUser;
        this.offerService = offerService;
    }

    @GetMapping("/home")
    public String home( Model model) {
        if (currentUser.getId() == 0) {
            return "redirect:/";
        }
        model.addAttribute("userName", currentUser);

        List<Offer> allOffers=offerService.getAllOffers();





        return "home";
    }
}
