package com.openfaas.function;

import com.openfaas.function.model.CalculatorData;
import com.openfaas.function.model.ResultData;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class Handler implements io.vertx.core.Handler<RoutingContext> {

    public void handle(RoutingContext routingContext) {
        CalculatorData data = routingContext.getBodyAsJson().mapTo(CalculatorData.class);
        Double result = Calculator.calculate(data);
        ResultData resultData = ResultData.from(data, result);
        routingContext.response()
                .putHeader("content-type", "application/json;charset=UTF-8")
                .end(Json.encode(resultData));
    }
}
