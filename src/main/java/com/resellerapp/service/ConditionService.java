package com.resellerapp.service;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionService {
    private ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public Condition findByName(String condition) {
        //TODO
        conditionRepository.findByConditionName(condition.);
    }
}
