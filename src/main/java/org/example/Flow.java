package org.example;

import lombok.Data;

import java.util.List;

@Data
public class Flow {
    private String id;
    private List<Step> steps;
}
