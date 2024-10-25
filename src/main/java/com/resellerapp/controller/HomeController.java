package com.resellerapp.controller;

import com.resellerapp.model.dtos.BoughtOfferDto;
import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private CurrentUser currentUser;
    private OfferService offerService;
    private UserService userService;
    private ModelMapper modelMapper;

    public HomeController(CurrentUser currentUser, OfferService offerService, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.userService = userService;

        this.modelMapper = modelMapper;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (currentUser.getId() == 0) {
            return "redirect:/";
        }
        model.addAttribute("userName", currentUser);

        Set<Offer> myOffers = userService.offersByUser(currentUser.getId());
        List<MyOfferDto> offerDtoList = myOffers.stream()
                .map(o -> modelMapper.map(o, MyOfferDto.class))
                .collect(Collectors.toList());
        model.addAttribute("myOffers", offerDtoList);

        Set<Offer> boughtOffers = userService.boughtOffersByUser(currentUser.getId());
        List<BoughtOfferDto> boughtOfferDtoList = boughtOffers.stream()
                .map(o -> modelMapper.map(o, BoughtOfferDto.class))
                .collect(Collectors.toList());
        model.addAttribute("boughtOffers", boughtOfferDtoList);


        return "home";
    }
}
