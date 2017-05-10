package com.simpleproject.vertx;

import io.vertx.core.*;

import javax.inject.Inject;

/**
 * Created by otavio on 09/05/2017.
 */
public abstract class InjectableVerticle implements Verticle {

    @Inject
    private Vertx vertx;

    @Override
    public Vertx getVertx() {
        return vertx;
    }

    @Override
    public void init(Vertx vertx, Context context) {
        System.out.println("Injected vertx " + this.vertx);
        System.out.println("Param vertx " + vertx);
    }

    @Override
    public void start(Future<Void> future) throws Exception {
        start();
        future.complete();
    }

    @Override
    public void stop(Future<Void> future) throws Exception {
        stop();
        future.complete();
    }

    public void start() throws Exception {

    }

    public void stop() throws Exception {

    }

}
