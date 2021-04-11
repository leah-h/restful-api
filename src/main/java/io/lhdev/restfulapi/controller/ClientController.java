package io.lhdev.restfulapi.controller;

import io.lhdev.restfulapi.model.Client;

import java.util.Arrays;
import java.util.List;

public class ClientController {

    public List<Client> getAllClients() {
        List<Client> clients = Arrays.asList(
                new Client(1L,
                            12345L,
                        "Joe",
                        "Smith",
                        "joe.smith@test.com",
                         "123-456-7890",
                        "123 Fake St. Atlanta, GA 30307"),
                new Client(2L,
                        54321L,
                        "Jane",
                        "Doe",
                        "jane.doe@test.com",
                        "404-456-7890",
                        "564 Peachtree Rd. Atlanta, GA 30312")
                );
        return clients;
    }
}
