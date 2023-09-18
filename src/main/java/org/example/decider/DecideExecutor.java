package org.example.decider;

import org.example.Step;
import org.example.Transaction;
import org.example.repository.IDeciderTemplateRepository;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DecideExecutor {


    private final IDeciderTemplateRepository iDeciderTemplateRepository;

    public DecideExecutor(IDeciderTemplateRepository iDeciderTemplateRepository) {
        this.iDeciderTemplateRepository = iDeciderTemplateRepository;
    }

    /*
        Current step in Step
         */
    public String getTypeOfNextStep(Transaction transaction, Step step) {
        DeciderTemplate deciderTemplate = iDeciderTemplateRepository.getDeciderTemplateById(transaction.getFlow().getId()); //Here must be substituted by the correct search in database
        Integer IdCurrentDeciderNode = transaction.getDeciderModelTrail() != null &&
                                       !transaction.getDeciderModelTrail().isEmpty() ?
                                       transaction.getDeciderModelTrail().get(transaction.getDeciderModelTrail().size()) : 0;
        DeciderNode currentDeciderNode = deciderTemplate.getDeciderNodesMap().get(IdCurrentDeciderNode);
        if("FINISHED".equals(step.getResult()) || "BYPASS".equals(step.getResult())) {
            Integer idDeciderNodeDestiny =  currentDeciderNode.getConditionDecider().stream().filter(condition -> {
                try {
                    return evalCondition(condition, transaction);
                } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }).map(ConditionDecider::getIdDeciderNodeDestiny).findFirst().orElse(null);
            if(idDeciderNodeDestiny == null) {
                return "";
            }
            transaction.getDeciderModelTrail().add(idDeciderNodeDestiny);
            return deciderTemplate.getDeciderNodesMap().get(idDeciderNodeDestiny).getTypeStep();
        } else {
            if(currentDeciderNode.getIdDeciderNodeSubstitute() != null) {
                transaction.getDeciderModelTrail().add(currentDeciderNode.getIdDeciderNodeSubstitute());
                return deciderTemplate.getDeciderNodesMap().get(currentDeciderNode.getIdDeciderNodeSubstitute()).getTypeStep();
            }
        }
        return step.getType();
    }

    private boolean evalCondition(ConditionDecider condition, Transaction transaction) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(condition.getExpressionConditions().isEmpty() ||
                (!condition.getAgregationEnums().isEmpty() &&
                 condition.getExpressionConditions().size() <= condition.getAgregationEnums().size())) {
            throw new RuntimeException("Number of expressions and aggregations into condition are invalid");
        }
        var expressionResults = condition.getExpressionConditions().stream().map(expressionCondition -> {
            String valueToGet = getNestedProperty (transaction, expressionCondition.getPredicate());
            switch (expressionCondition.getOperator()) {
                case EQUALS -> { return expressionCondition.getExpectedResult().equals(valueToGet); }
                case BIGGER_OR_EQUALS_THAN -> {
                    return Double.parseDouble(expressionCondition.getExpectedResult()) >= Double.parseDouble(valueToGet);
                }
                case BIGGER_THAN -> { return Double.parseDouble(expressionCondition.getExpectedResult()) > Double.parseDouble(valueToGet); }
                case LESS_OR_EQUALS_THAN -> { return Double.parseDouble(expressionCondition.getExpectedResult()) <= Double.parseDouble(valueToGet); }
                case LESS_THAN -> { return Double.parseDouble(expressionCondition.getExpectedResult()) < Double.parseDouble(valueToGet); }
                case NOT_EQUALS -> { return !expressionCondition.getExpectedResult().equals(valueToGet); }
                default -> { return false; }
            }
        }).toList();
        if(!condition.getAgregationEnums().isEmpty()) {
            return evaluateResultWithAggregations(condition, expressionResults);
        }
        return expressionResults.get(0);
    }

    private static boolean evaluateResultWithAggregations(ConditionDecider condition, List<Boolean> expressionResults) {
        boolean accumulated = expressionResults.get(0);
        for (int i = 0; i < condition.getAgregationEnums().size(); i++) {
            switch (condition.getAgregationEnums().get(i)) {
                case AND -> {
                    accumulated = accumulated && expressionResults.get(i + 1);
                    break;
                }
                case OR -> {
                    accumulated = accumulated || expressionResults.get(i + 1);
                    break;
                }

            }
        }
        return accumulated;
    }

    private String getNestedProperty(Transaction transaction, String predicate) {
        try {
            return (String) PropertyUtils.getNestedProperty (transaction, predicate);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
