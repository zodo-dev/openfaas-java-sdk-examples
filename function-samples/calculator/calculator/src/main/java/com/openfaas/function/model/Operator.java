package com.openfaas.function.model;

public enum Operator {
    SUM() {
        @Override
        public Double calculate(Double value1, Double value2) {
            return nullToZero(value1) + nullToZero(value1);
        }
    },
    MULTIPLY() {
        @Override
        public Double calculate(Double value1, Double value2) {
            return nullToZero(value1) * nullToZero(value1);
        }
    };

    public abstract Double calculate(Double value1, Double value2);

    private static Double nullToZero(Double value) {
        return value != null ? value : 0;
    }
}
