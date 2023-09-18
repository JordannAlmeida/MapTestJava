package org.example.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.decider.DeciderTemplate;

public class DeciderTemplateMockImpl implements IDeciderTemplateRepository{
    @Override
    public DeciderTemplate getDeciderTemplateById(String journeyId) throws JsonProcessingException {
        String json = "{ \"offerId\": \"bvudsbvusbd\", \"journeyId\": \"" + journeyId + "\", \"flowId\": \"ascascasc\", \"deciderNodesMap\": { \"0\": { \"id\": 0, \"typeStep\": \"BIG_DATA\", \"conditionDecider\": [ { \"expressionConditions\": [ { \"predicate\": \"resultA.score\", \"operator\": \"BIGGER_THAN\", \"expectedResult\": \"700\" }, { \"predicate\": \"resultB.employer.status\", \"operator\": \"EQUALS\", \"expectedResult\": \"ACTIVE\" } ], \"agregationEnums\": [ \"AND\" ], \"idDeciderNodeDestiny\": 2 } ], \"idDeciderNodeSubstitute\": 1 }, \"1\": { \"id\": 1, \"typeStep\": \"THINK_DATA\", \"stepResult\": \"SUCESS\", \"conditionDecider\": [ { \"expressionConditions\": [ { \"predicate\": \"resultA.thinkData.score\", \"operator\": \"BIGGER_THAN\", \"expectedResult\": \"700\" }, { \"predicate\": \"resultB.register.status\", \"operator\": \"EQUALS\", \"expectedResult\": \"ACTIVE\" } ], \"agregationEnums\": [ \"OR\" ], \"idDeciderNodeDestiny\": 2 } ], \"idDeciderNodeSubstitute\": null }, \"2\": { \"id\": 2, \"typeStep\": \"TELESIGN\", \"conditionDecider\": [], \"idDeciderNodeSubstitute\": null } } }";
        ObjectMapper obj =  new ObjectMapper();
        return obj.readValue(json, DeciderTemplate.class);
    }
}
