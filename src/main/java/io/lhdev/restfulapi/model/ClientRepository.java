package io.lhdev.restfulapi.model;

import java.util.Arrays;
import java.util.List;

public class ClientRepository {

    public List<Client> getAllClients() {
        List<Client> clients = Arrays.asList(
                new Client(1,
                        12345,
                        "Joe",
                        "Smith",
                        "joe.smith@test.com",
                        "123-456-7890",
                        "123 Fake St." ,
                        "Atlanta",
                        "GA",
                        "30307"),
                new Client(2,
                        54321,
                        "Jane",
                        "Doe",
                        "jane.doe@test.com",
                        "404-456-7890",
                        "564 Peachtree Rd.",
                        "Atlanta",
                        "GA",
                        "30312")
        );
        return clients;
    }

}
