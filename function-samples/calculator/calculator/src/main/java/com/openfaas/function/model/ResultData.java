package com.openfaas.function.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResultData {
    private final Operator operator;
    private final Double value1;
    private final Double value2;
    private final Double result;

    public static ResultData from(CalculatorData data, Double result) {
        return new ResultData(data.getOperator(), data.getValue1(), data.getValue2(), result);
    }
}
