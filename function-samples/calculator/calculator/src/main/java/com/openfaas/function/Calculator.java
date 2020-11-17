package com.openfaas.function;

import com.openfaas.function.model.CalculatorData;

public final class Calculator {
    private Calculator() {
    }

    public static Double calculate(CalculatorData data) {
        if (data.getOperator() == null) {
            return null;
        }
        return data.getOperator().calculate(data.getValue1(), data.getValue2());
    }
}
