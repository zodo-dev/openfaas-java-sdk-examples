package com.openfaas.function.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CalculatorData {
    private Operator operator;
    private Double value1;
    private Double value2;
}
