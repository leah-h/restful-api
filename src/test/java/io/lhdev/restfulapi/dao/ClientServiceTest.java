package io.lhdev.restfulapi.dao;

import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.service.ClientService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class ClientServiceTest {

    // Fake repository dependency(mocked with Mockito)
    private static ClientRepository mockClientRepository;

    // The system, under test, our ClientService instance
    private ClientService clientService;

    @Before
    public void setUp() throws Exception {
        // Before each test, instantiate a new client, passing in our fake client repository
        clientService = new ClientService(mockClientRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void itShouldGetAllClients() throws DatabaseException{
        try {
            clientService.getAllClients();

        } catch(DatabaseException e) {
            assertEquals(e.getMessage(), "Unable to connect to the database");
        }
    }

    @Test
    public void itShouldGetClientById() {
        // Given
        // When
        // Then
    }

    @Test
    public void itShouldAddClient() {
        // Given
        // When
        // Then
    }

    @Test
    public void itShouldDeleteClientById() {
        // Given
        // When
        // Then
    }

    @Test
    public void itShouldUpdateClientById() {
        // Given
        // When
        // Then
    }
}










