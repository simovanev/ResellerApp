package com.resellerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

    @GetMapping("/addOffer")
    public String addOffer() {
        return "offer-add";
    }
}
