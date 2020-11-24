package dev.zodo.openfaas.examples.fcall.call;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class CallScheduled {

    @Scheduled(fixedDelay = 15_000)
    public void callSync() {
        LocalTime time = LocalTime.now();
        CallFunction.callSyncCalculator(time.getMinute(), time.getSecond());
    }

    @Scheduled(fixedDelay = 16_000, initialDelay = 10_000)
    public void callAsync() {
        LocalTime time = LocalTime.now();
        CallFunction.asyncCallSyncCalculator(time.getMinute(), time.getSecond());
    }
}
