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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class CallFunction {

    @Value("${openfaas.provider.url}")
    private String url;
    @Value("${openfaas.provider.username}")
    private String username;
    @Value("${openfaas.provider.password}")
    private String password;

    private OpenfaasApi openfaasApi() {
        return OpenfaasApi.getInstance(URI.create(url), username, password);
    }

    public void callSyncCalculator(double value1, double value2) {
        log.info("[callSyncCalculator] {}", LocalDateTime.now());
        CalculatorData data = new CalculatorData(Operator.SUM, value1, value2);
        SyncRequest<CalculatorData> request = new SyncRequest<>("calculator", data);
        SyncResponse<ResultData> response = openfaasApi().callFunction(request, ResultData.class);
        ResultData resultData = response.getBody();
        log.info("Duration {}ms", Optional.ofNullable(response.getDurationSeconds())
                .map(Duration::toMillis)
                .orElse(null));
        log.info("{} {} {} = {}", resultData.getValue1(), resultData.getOperator().getSymbol(), resultData.getValue2(), resultData.getResult());
    }

    public void asyncCallSyncCalculator(double value1, double value2) {
        log.info("[asyncCallSyncCalculator] {}", LocalDateTime.now());
        CalculatorData data = new CalculatorData(Operator.SUM, value1, value2);
        AsyncRequest<CalculatorData> request = new AsyncRequest<>("calculator", "http://10.0.0.31:8080/api/openfaas/async-callback", data);
        AsyncResponse response = openfaasApi().callAsyncFunction(request);
        log.info("CallId {}", response.getCallId());
        log.info("StartTime {}", response.getStartTime());
        log.info("StatusCode {}", response.getStatusCode());
    }
}
