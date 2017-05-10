package com.simpleproject.vertx.verticle;

import com.simpleproject.Disabled;
import com.simpleproject.vertx.InjectableVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by otavio on 09/05/2017.
 */
@Disabled
@ApplicationScoped
public class HTTPVerticle extends InjectableVerticle {

    private HttpServer httpServer;

    @Inject
    private Vertx vertx;

    @Override
    public void start() throws Exception {
        this.httpServer = vertx.createHttpServer().requestHandler(req -> req.response()
                .end("Hello from CDI!")).listen(8080);
    }

    @Override
    public void stop() throws Exception {
        this.httpServer.close();
    }
}
