package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
public class Offer extends BaseId {
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String description;
    @Column(nullable = false)
    private Double price;
    @ManyToOne
    private Condition condition;
    @ManyToOne
    private User ownedBy;
    @ManyToOne
    private User boughtBy;
    private boolean isSold = false;

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }



    public Offer() {
    }

    public @Size(min = 2, max = 50) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 2, max = 50) String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ConditionName getCondition() {
        return condition.getConditionName();
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public User getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(User ownedBy) {
        this.ownedBy = ownedBy;
    }

    public User getBoughtBy() {
        return boughtBy;
    }

    public void setBoughtBy(User boughtBy) {
        this.boughtBy = boughtBy;
    }
}
