package com.resellerapp.controller;

import com.resellerapp.model.dtos.BoughtOfferDto;
import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.dtos.OtherOfferDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.service.OfferService;
import com.resellerapp.service.UserService;
import com.resellerapp.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private CurrentUser currentUser;
    private OfferService offerService;
    private ModelMapper modelMapper;

    public HomeController(CurrentUser currentUser, OfferService offerService, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (currentUser.getId() == 0) {
            return "redirect:/";
        }
        model.addAttribute("userName", currentUser.getName());

        Set<Offer> myOffers = offerService.offersByUser(currentUser.getId());
        List<MyOfferDto> offerDtoList = myOffers.stream()
                .map(o -> modelMapper.map(o, MyOfferDto.class))
                .collect(Collectors.toList());
        model.addAttribute("myOffers", offerDtoList);

        Set<Offer> boughtOffers = offerService.boughtOffersByUser(currentUser.getId());
        List<BoughtOfferDto> boughtOfferDtoList = boughtOffers.stream()
                .map(o -> modelMapper.map(o, BoughtOfferDto.class))
                .collect(Collectors.toList());
        model.addAttribute("boughtOffers", boughtOfferDtoList);

        Set<Offer> otherOffers = offerService.offersByUserNot(currentUser.getId());
        List<OtherOfferDto> allOtherOffersList = otherOffers.stream()
                .filter(o->!o.isSold())
                .map(o -> modelMapper.map(o, OtherOfferDto.class))
                .collect(Collectors.toList());

        for(OtherOfferDto otherOfferDto : allOtherOffersList){
            int id =otherOfferDto.getOfferId();
            for (Offer offer:otherOffers){
                if (offer.getId()==id){
                    otherOfferDto.setOwnedBy(offer.getOwnedBy().getUsername());
                }
            }
        }



        model.addAttribute("otherOffers", allOtherOffersList);
        model.addAttribute("totalSize", allOtherOffersList.size());

        return "home";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable int id, RedirectAttributes redirectAttributes) {

        try {
            offerService.removeOffer(id);
            redirectAttributes.addFlashAttribute("success", "Removed offer successfully");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error removing offer");
        }
        return "redirect:/home";

    }
    @GetMapping("/buy/{id}")
    public String byy(@PathVariable int id, RedirectAttributes redirectAttributes) {
        offerService.buyOffer(id);
        return "redirect:/home";
    }
}
