package dev.zodo.openfaas.examples.fcall.vo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultData {
    private Operator operator;
    private Double value1;
    private Double value2;
    private Double result;
}
