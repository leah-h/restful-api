package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.model.ClientRepository;

import java.util.List;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public Client getAllClients(){
        List<Client> clients = clientRepository.getAllClients();
        return (Client) clients;
    }

}
