package com.resellerapp.model.dtos;

public class MyOfferDto {
    private int id;
    private String conditionName;
    private double price;
    private String description;

    public String getCondition() {
        return conditionName;
    }

    public void setCondition(String condition) {
        this.conditionName = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
