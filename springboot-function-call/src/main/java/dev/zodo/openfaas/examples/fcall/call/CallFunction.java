package dev.zodo.openfaas.examples.fcall.call;

import dev.zodo.openfaas.api.OpenfaasApi;
import dev.zodo.openfaas.api.async.AsyncRequest;
import dev.zodo.openfaas.api.async.AsyncResponse;
import dev.zodo.openfaas.api.sync.SyncRequest;
import dev.zodo.openfaas.api.sync.SyncResponse;
import dev.zodo.openfaas.examples.fcall.vo.CalculatorData;
import dev.zodo.openfaas.examples.fcall.vo.Operator;
import dev.zodo.openfaas.examples.fcall.vo.ResultData;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public class CallFunction {
    public static void main(String[] args) {
        callSyncCalculator(10d, 20d);
        asyncCallSyncCalculator(10d, 20d);
    }

    public static void callSyncCalculator(double value1, double value2) {
        log.info("[callSyncCalculator] {}", LocalDateTime.now());
        CalculatorData data = new CalculatorData(Operator.SUM, value1, value2);
        SyncRequest<CalculatorData> request = new SyncRequest<>("calculator", data);
        SyncResponse<ResultData> response = OpenfaasApi.getInstance().callFunction(request, ResultData.class);
        ResultData resultData = response.getBody();
        log.info("Duration {}ms", Optional.ofNullable(response.getDurationSeconds())
                .map(Duration::toMillis)
                .orElse(null));
        log.info("{} {} {} = {}", resultData.getValue1(), resultData.getOperator().getSymbol(), resultData.getValue2(), resultData.getResult());
    }

    public static void asyncCallSyncCalculator(double value1, double value2) {
        log.info("[asyncCallSyncCalculator] {}", LocalDateTime.now());
        CalculatorData data = new CalculatorData(Operator.SUM, value1, value2);
        AsyncRequest<CalculatorData> request = new AsyncRequest<>("calculator", "http://10.0.0.31:8080/api/openfaas/async-callback", data);
        AsyncResponse<ResultData> response = OpenfaasApi.getInstance().callAsyncFunction(request, ResultData.class);
        log.info("CallId {}", response.getCallId());
        log.info("StartTime {}", response.getStartTime());
        log.info("StatusCode {}", response.getStatusCode());
    }
}
