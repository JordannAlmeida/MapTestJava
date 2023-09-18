package org.example;

import lombok.Data;

import java.util.List;

@Data
public class Transaction {
    private String id;
    private Flow flow;
    private Object resultA;
    private Object resultB;

    //New properties
    private List<Integer> deciderModelTrail;
}
