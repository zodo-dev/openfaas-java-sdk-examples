package dev.zodo.openfaas.examples.fcall.callback;

import dev.zodo.openfaas.examples.fcall.vo.ResultData;
import dev.zodo.openfaas.webhook.CallbackAsyncEndpoint;

import javax.ws.rs.Path;

@Path("/api")
public class CallbackWebhookResource extends CallbackAsyncEndpoint<ResultData> {

}
