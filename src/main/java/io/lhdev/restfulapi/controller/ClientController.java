package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.model.ClientRepository;
import io.lhdev.restfulapi.service.ClientService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientController implements Controller{

    private ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    private Handler getAllClients = ctx -> {
//        List<Client> clients = Arrays.asList(
//                new Client(1L,
//                        12345L,
//                        "Joe",
//                        "Smith",
//                        "joe.smith@test.com",
//                        "123-456-7890",
//                        "123 Fake St. Atlanta, GA 30307"),
//                new Client(2L,
//                        54321L,
//                        "Jane",
//                        "Doe",
//                        "jane.doe@test.com",
//                        "404-456-7890",
//                        "564 Peachtree Rd. Atlanta, GA 30312")
//        );



//
//        ctx.json(listOfClients);
    };



    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients", getAllClients);
    }
}


