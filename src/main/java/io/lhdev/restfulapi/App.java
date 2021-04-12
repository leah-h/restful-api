package io.lhdev.restfulapi;

import io.javalin.Javalin;

import io.lhdev.restfulapi.controller.AccountController;
import io.lhdev.restfulapi.controller.ClientController;
import io.lhdev.restfulapi.controller.Controller;

public class App {

    private static Javalin app;

    public static void main(String[] args) {
        app = Javalin.create();

        mapControllers(new ClientController(), new AccountController());

        app.start(7000);
    }

    public static void mapControllers(Controller... controllers){
        for (Controller c: controllers){
            c.mapEndpoints(app);
        }
    }


}
