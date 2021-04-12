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

        mapControllers(new ClientController(), new AccountController());

        app.start(7000);

        logger.info("Hello Logs from {}", App.class.getSimpleName());
    }

    public static void mapControllers(Controller... controllers){
        for (Controller c: controllers){
            c.mapEndpoints(app);
        }
    }


}
