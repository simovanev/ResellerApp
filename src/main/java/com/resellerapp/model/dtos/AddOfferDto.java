package com.resellerapp.model.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class AddOfferDto {
    @Size(min = 2, max = 50)
    @NotNull
    private int description;
    @NotNull
    @Positive
    private double price;
    @NotNull
    private String condition;

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
