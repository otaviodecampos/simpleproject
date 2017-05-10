package com.simpleproject.event;

import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

/**
 * Created by otavio on 09/05/2017.
 */
@ApplicationScoped
public class EventReceiver {
    public void onString(@Observes String msg) {
        System.out.println("Received the text message " + msg);
    }

    public void onSilly(@Observes JsonObject json) {
        System.out.println("Received JSON Payload" + json.getString("foo"));
    }

}
