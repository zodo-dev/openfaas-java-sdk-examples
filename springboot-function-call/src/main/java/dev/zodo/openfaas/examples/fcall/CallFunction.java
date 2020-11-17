package dev.zodo.openfaas.examples.fcall;

import dev.zodo.openfaas.api.OpenfaasApi;
import dev.zodo.openfaas.api.SyncRequest;

import java.util.Collections;

public class CallFunction {
    public static void main(String[] args) {
        OpenfaasApi api = new OpenfaasApi();
        SyncRequest<String> request = new SyncRequest<>("", "ok", Collections.emptyMap());
        api.callFunction(request);
    }
}
