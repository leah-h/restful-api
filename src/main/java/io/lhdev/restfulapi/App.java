package io.lhdev.restfulapi;

import io.javalin.Javalin;

import io.lhdev.restfulapi.controller.AccountController;
import io.lhdev.restfulapi.controller.ClientController;
import io.lhdev.restfulapi.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static Javalin app;

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        app = Javalin.create();

        app.before(ctx -> {
            String URI = ctx.req.getRequestURI();
            String httpMethod = ctx.req.getMethod();
            logger.info(httpMethod + " request to endpoint " + URI + " received");
        });

        mapControllers(new ClientController(), new AccountController());

        app.start(7000);

    }

    public static void mapControllers(Controller... controllers){
        for (Controller c: controllers){
            c.mapEndpoints(app);
        }
    }


}
