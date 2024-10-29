package com.resellerapp.service;

import com.resellerapp.model.dtos.AddOfferDto;
import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.repository.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Service
public class OfferService {
    private OfferRepository offerRepository;
    private ModelMapper modelMapper;

    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }


    public Set<Offer> offersByUserNot(int id) {
        return offerRepository.findByOwnedByIdAndBoughtByIdNot(id, id);
    }

    public Set<Offer> offersByUser(int id) {
        return offerRepository.findByOwnedById(id);
    }

    public Set<Offer> boughtOffersByUser(int id) {
        return offerRepository.findByBoughtById(id);
    }

    public void addOffer(AddOfferDto addOfferDto) {
        Offer offer = modelMapper.map(addOfferDto, Offer.class);
        offerRepository.save(offer);
    }
}
