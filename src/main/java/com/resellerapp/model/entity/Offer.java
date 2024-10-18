package com.resellerapp.model.entity;

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

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
