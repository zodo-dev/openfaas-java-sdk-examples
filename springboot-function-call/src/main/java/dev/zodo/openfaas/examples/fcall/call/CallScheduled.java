package dev.zodo.openfaas.examples.fcall.call;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class CallScheduled {

    private final CallFunction callFunction;

    @Scheduled(fixedDelay = 15_000)
    public void callSync() {
        LocalTime time = LocalTime.now();
        callFunction.callSyncCalculator(time.getMinute(), time.getSecond());
    }

    @Scheduled(fixedDelay = 16_000, initialDelay = 10_000)
    public void callAsync() {
        LocalTime time = LocalTime.now();
        callFunction.asyncCallSyncCalculator(time.getMinute(), time.getSecond());
    }
}
