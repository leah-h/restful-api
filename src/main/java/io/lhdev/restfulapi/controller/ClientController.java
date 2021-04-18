package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ClientController implements Controller{

    private Logger logger = LoggerFactory.getLogger(ClientController.class);
    private ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    private Handler getAllClients = ctx -> {
        List<Client> listOfClients = clientService.getAllClients();
        ctx.json(listOfClients);
    };



    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients", getAllClients);
    }
}


