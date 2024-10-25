package com.resellerapp.service;

import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OfferService {
    private OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
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
}
