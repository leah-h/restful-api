package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;

public interface Controller {

    void mapEndpoints(Javalin app);
}
