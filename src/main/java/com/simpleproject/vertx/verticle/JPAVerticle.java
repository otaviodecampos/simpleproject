package com.simpleproject.vertx.verticle;

import com.simpleproject.vertx.InjectableVerticle;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by otavio on 12/05/2017.
 */
@ApplicationScoped
public class JPAVerticle extends InjectableVerticle {

    private EntityManagerFactory entityManagerFactory;

    @Override
    public void start() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("simpleproject");
    }

}
