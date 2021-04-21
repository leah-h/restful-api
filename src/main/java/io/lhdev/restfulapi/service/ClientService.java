package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.exceptions.ClientCreationException;
import io.lhdev.restfulapi.exceptions.ClientNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.dao.ClientRepository;
import org.slf4j.Logger;

import java.util.List;

public class ClientService {

    private Logger logger;

    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    // For passing in the mocked clientRepository for unit testing
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() throws DatabaseException {

        List<Client> listOfClients = clientRepository.getAllClients();
        if (listOfClients != null){
            return listOfClients;
        }
        throw new DatabaseException("Unable to connect to the database");
    }

    public Client getClientById(int id) throws ClientNotFoundException, DatabaseException {
        Client client = clientRepository.getClientById(id);
        if (client == null){
            throw new ClientNotFoundException("Client with id: " + id + " not found.");
        }

        return client;
    }

    public Client addClient(Client client) throws DatabaseException, ClientCreationException {
        Client addedClient = clientRepository.addClient(client);
        if (addedClient == null) {
            throw new ClientCreationException("Unable to add client to database.");
        }

        return addedClient;
    }

    public void deleteClientById(int id) throws DatabaseException, ClientNotFoundException {
        clientRepository.deleteClientById(id);
    }

    public Client updateClientById(Client client)
            throws DatabaseException, ClientNotFoundException{

        return clientRepository.updateClientById(client);
    }

}
