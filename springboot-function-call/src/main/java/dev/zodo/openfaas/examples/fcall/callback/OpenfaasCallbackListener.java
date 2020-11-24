package dev.zodo.openfaas.examples.fcall.callback;

import dev.zodo.openfaas.api.async.AsyncCallbackResponse;
import dev.zodo.openfaas.examples.fcall.vo.ResultData;
import dev.zodo.openfaas.webhook.OpenfaasCallbackEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Slf4j
@Component
public class OpenfaasCallbackListener implements OpenfaasCallbackEvent<ResultData> {

    @Override
    public void consume(AsyncCallbackResponse<ResultData> asyncResponse) {
        log.info("Receive event: {}", asyncResponse.getFunctionName());
        Optional.ofNullable(asyncResponse.getBody()).ifPresent(resultData -> {
            log.info("CallId {}", asyncResponse.getCallId());
            log.info("Duration {}ms", Optional.ofNullable(asyncResponse.getDurationSeconds())
                    .map(Duration::toMillis)
                    .orElse(null));
            log.info("StatusCode {}", asyncResponse.getFunctionStatus());
            log.info("{} {} {} = {}", resultData.getValue1(), resultData.getOperator().getSymbol(), resultData.getValue2(), resultData.getResult());
        });
    }
}
