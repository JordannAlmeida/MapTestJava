package org.example.decider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.decider.enuns.OperatorEnum;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionCondition {
    private String predicate;
    private OperatorEnum operator;
    private String expectedResult;
}
