package org.example.decider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeciderNode {
    private int id;
    private String typeStep;
    private String stepResult;
    private List<ConditionDecider> conditionDecider;
    private Integer idDeciderNodeSubstitute;
}
