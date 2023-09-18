package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String id;
    private Flow flow;
    private Object resultA;
    private Object resultB;

    //New properties
    private List<Integer> deciderModelTrail;
}
