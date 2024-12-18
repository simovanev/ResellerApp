package com.resellerapp.controller;

import com.resellerapp.model.dtos.AddOfferDto;
import com.resellerapp.service.OfferService;
import org.modelmapper.ModelMapper;
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
public class OfferController {
    private OfferService offerService;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
    }

    @ModelAttribute("addOfferDto")
    public AddOfferDto addAttributes() {
        return new AddOfferDto();
    }


    @GetMapping("/addOffer")
    public String addOffer() {
        return "offer-add";
    }

    @PostMapping("/addOffer")
    public String addOffer(@Valid AddOfferDto addOfferDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(addOfferDto.getCondition().equals("0")){
            FieldError fieldError = new FieldError(
                    "addOfferDto", "condition", "Choose Condition!!!");
            bindingResult.addError(fieldError);
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOfferDto", bindingResult);
            return "redirect:/addOffer";
        }
        offerService.addOffer(addOfferDto);
        return "redirect:/home";
    }
}
