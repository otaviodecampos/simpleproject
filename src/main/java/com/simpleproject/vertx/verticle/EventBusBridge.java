package com.simpleproject.vertx.verticle;

import com.simpleproject.Disabled;
import com.simpleproject.vertx.InjectableVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * Created by otavio on 09/05/2017.
 */
@Disabled
@ApplicationScoped
public class EventBusBridge extends InjectableVerticle {

    @Inject
    private BeanManager beanManager;

    @Inject
    private Vertx vertx;

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();
        eventBus.consumer("some-address", message -> beanManager.fireEvent(message.body()));
        vertx.setPeriodic(1000, value -> eventBus.publish("some-address", "Some text here " + value));
        vertx.setPeriodic(1500, value -> eventBus.publish("some-address", new JsonObject().put("foo", "bar " + value)));
    }

}
