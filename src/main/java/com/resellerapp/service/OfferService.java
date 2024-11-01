package com.resellerapp.service;

import com.resellerapp.model.dtos.AddOfferDto;
import com.resellerapp.model.dtos.MyOfferDto;
import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.Offer;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.OfferRepository;
import com.resellerapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class OfferService {
    private OfferRepository offerRepository;
    private ModelMapper modelMapper;
    private UserService userService;
    private ConditionService conditionService;
    private UserRepository userRepository;


    public OfferService(OfferRepository offerRepository, ModelMapper modelMapper, UserService userService, ConditionService conditionService, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.conditionService = conditionService;
        this.userRepository = userRepository;
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

    @Transactional
    public void addOffer(AddOfferDto addOfferDto) {
        Offer offer = modelMapper.map(addOfferDto, Offer.class);
        offer.setCondition(conditionService.findByName(addOfferDto.getCondition()));
        User user = userService.userByCurrentUserId();
        offer.setOwnedBy(user);
        user.getOffers().add(offer);
        userRepository.save(user);
       // offerRepository.save(offer);

    }
}
