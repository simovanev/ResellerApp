package com.resellerapp.model.entity;

import com.resellerapp.model.enums.ConditionName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Target;

@Entity
@Table()
public class Condition  extends BaseId{

    @Column(nullable = false,unique = true)
    private ConditionName conditionName;
    @Column(nullable = false)
    private String description;

    public Condition() {
    }

    public ConditionName getConditionName() {
        return conditionName;
    }

    public void setConditionName( ConditionName conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
