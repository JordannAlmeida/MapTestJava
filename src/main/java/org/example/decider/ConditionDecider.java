package org.example.decider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.decider.enuns.AggregationEnum;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDecider {
    private List<ExpressionCondition> expressionConditions;
    private List<AggregationEnum> agregationEnums;
    private Integer idDeciderNodeDestiny;
}
