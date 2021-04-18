package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.exceptions.ClientCreationException;
import io.lhdev.restfulapi.exceptions.ClientNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.dao.ClientRepository;

import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    // For passing in the mocked clientRepository for unit testing
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() throws DatabaseException {
        return clientRepository.getAllClients();
    }

    public Client getClientById(int id) throws ClientNotFoundException, DatabaseException {
        return clientRepository.getClientById(id);
    }

    public Client addClient(Client client) throws DatabaseException, ClientCreationException {
        return clientRepository.addClient(client);
    }

}
