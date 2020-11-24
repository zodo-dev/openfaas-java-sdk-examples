package dev.zodo.openfaas.examples.fcall.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operator {
    SUM("+") {
        @Override
        public Double calculate(Double value1, Double value2) {
            return nullToZero(value1) + nullToZero(value2);
        }
    },
    MULTIPLY("*") {
        @Override
        public Double calculate(Double value1, Double value2) {
            return nullToZero(value1) * nullToZero(value2);
        }
    };

    private final String symbol;

    public abstract Double calculate(Double value1, Double value2);

    private static Double nullToZero(Double value) {
        return value != null ? value : 0;
    }
}
