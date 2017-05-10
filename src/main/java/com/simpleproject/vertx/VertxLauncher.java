package com.simpleproject.vertx;

import com.simpleproject.Disabled;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Created by otavio on 09/05/2017.
 */
@ApplicationScoped
public class VertxLauncher {

    private Vertx vertx;

    @Inject
    private Instance<Verticle> allDiscoveredVerticles;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object object) {
        this.vertx = Vertx.vertx();

        allDiscoveredVerticles.forEach(verticle -> {
            if (verticle.getClass().getAnnotation(Disabled.class) == null) {
                System.out.println("Found verticle " + verticle);
                vertx.deployVerticle(verticle);
            }
        });
    }

    @Produces
    @ApplicationScoped
    public Vertx getVertx() {
        return vertx;
    }

    @PreDestroy
    public void shutdown() {
        this.vertx.close();
    }

}
