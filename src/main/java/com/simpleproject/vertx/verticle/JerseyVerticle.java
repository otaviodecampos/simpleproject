package com.simpleproject.vertx.verticle;

import com.simpleproject.ApplicationManager;
import com.simpleproject.service.ServiceTest;
import com.simpleproject.vertx.InjectableVerticle;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Created by otavio on 09/05/2017.
 */
@ApplicationScoped
public class JerseyVerticle extends InjectableVerticle {

    private static final String SERVER_ADDRESS = "0.0.0.0";

    private static final int SERVER_PORT = 8080;

    private static final String RESOURCE_SCAN_PACKAGE = "com.simpleproject";

    private static final String API_CONTEXT_PATH = "/api";

    @Override
    public void start() throws Exception {
        HttpServer httpServer = new HttpServer();
        NetworkListener nl = new NetworkListener("listener", SERVER_ADDRESS, SERVER_PORT);
        httpServer.addListener(nl);

        HttpHandler handler = ContainerFactory.createContainer(GrizzlyHttpContainer.class, new ResourceConfig().packages(RESOURCE_SCAN_PACKAGE));
        httpServer.getServerConfiguration().addHttpHandler(handler, API_CONTEXT_PATH);

        httpServer.start();
    }
}
