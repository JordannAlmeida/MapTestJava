package org.example.decider;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/*

Example json:
{
    "offerId": "",
    "journeyId": "",
    "flowId": "",
    "deciderNodesMap": {
        "0": {
            "id": 0,
            "typeStep": "BIG_DATA",
            "conditionDecider": [
                {
                    "expressionConditions": [
                        {
                            "predicate": "resultA.score",
                            "operator": "BIGGER_THAN",
                            "expectedResult": "700"
                        },
                        {
                            "predicate": "resultB.employer.status",
                            "operator": "EQUALS",
                            "expectedResult": "ACTIVE"
                        }
                    ],
                    "agregationEnums": [
                        "AND"
                    ],
                    "idDeciderNodeDestiny": 2
                }
            ],
            "idDeciderNodeSubstitute": 1
        },
        "1": {
            "id": 1,
            "typeStep": "THINK_DATA",
            "stepResult": "SUCESS",
            "conditionDecider": [
                {
                    "expressionConditions": [
                        {
                            "predicate": "resultA.thinkData.score",
                            "operator": "BIGGER_THAN",
                            "expectedResult": "700"
                        },
                        {
                            "predicate": "resultB.register.status",
                            "operator": "EQUALS",
                            "expectedResult": "ACTIVE"
                        }
                    ],
                    "agregationEnums": [
                        "OR"
                    ],
                    "idDeciderNodeDestiny": 2
                }
            ],
            "idDeciderNodeSubstitute": null
        },
        "2": {
            "id": 2,
            "typeStep": "TELESIGN",
            "conditionDecider": [],
            "idDeciderNodeSubstitute": null
        }
    }
}
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeciderTemplate {
    private String offerId;
    private String journeyId;
    private String flowId;
    private Map<Integer, DeciderNode> deciderNodesMap;
}
