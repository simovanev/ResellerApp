package com.resellerapp.repository;

import com.resellerapp.model.entity.Offer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    @Override
    List<Offer> findAll();

    Set<Offer> findByOwnedByIdNot(int ownedBy_id);

    Set<Offer> findByOwnedById(int id);

    Set<Offer> findByBoughtById(int id);


}
