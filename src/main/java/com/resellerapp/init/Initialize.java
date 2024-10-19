package com.resellerapp.init;


import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import com.resellerapp.repository.ConditionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class Initialize implements CommandLineRunner {
    private final ConditionRepository conditionRepo;

    public Initialize(ConditionRepository conditionRepo) {
        this.conditionRepo = conditionRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (conditionRepo.count() == 0) {
            List<Condition> conditions = Arrays.stream(ConditionName.values())
                    .map(Condition::new)
                    .collect(Collectors.toList());

            for (Condition condition : conditions) {
                if (condition.getConditionName().toString().equals("EXCELLENT")) {
                    condition.setDescription("In perfect condition");
                } else if (condition.getConditionName().toString().equals("GOOD")) {
                    condition.setDescription("Some signs of wear and tear or minor defects");
                } else if (condition.getConditionName().toString().equals("ACCEPTABLE")) {
                    condition.setDescription("The item is fairly worn but continues to function properly");
                }
            }
            conditionRepo.saveAll(conditions);
        }

    }
}

