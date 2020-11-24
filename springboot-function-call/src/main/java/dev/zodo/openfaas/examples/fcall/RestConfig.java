package dev.zodo.openfaas.examples.fcall;

import dev.zodo.openfaas.examples.fcall.callback.CallbackWebhookResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class RestConfig extends ResourceConfig {

    public RestConfig() {
        register(CallbackWebhookResource.class);
        register(RestConfig.class);
    }

}
