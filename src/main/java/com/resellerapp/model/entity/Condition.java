package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.*;


@Entity
@Table(name = "conditions")
public class Condition extends BaseId {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ConditionName conditionName;
    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public Condition(ConditionName conditionName) {
        this.conditionName = conditionName;
    }

    @Enumerated(EnumType.STRING)
    public ConditionName getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
