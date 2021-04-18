package io.lhdev.restfulapi.dao;

import io.lhdev.restfulapi.exceptions.*;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientRepository {

    private Connection connection;

    public ClientRepository() {
        super();
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public List<Client> getAllClients() throws DatabaseException {
        List<Client> listOfClients = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM clients";

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                int acctNumber = rs.getInt("accountId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip  = rs.getString("zip");

                Client client = new Client(id, acctNumber, firstName, lastName, email,
                        phoneNumber, address, city, state, zip);
                listOfClients.add(client);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Unable to connect: " + e.getMessage());
        }
        return listOfClients;
    }

    public Client getClientById(int id) throws ClientNotFoundException, DatabaseException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM clients c WHERE c.id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int clientId = rs.getInt("id");
                int acctNumber = rs.getInt("accountId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip  = rs.getString("zip");

                return new Client(clientId, acctNumber, firstName, lastName, email,
                        phoneNumber, address, city, state, zip);

            }
        } catch (SQLException e) {
            throw new DatabaseException(("Error occurred with the database: " + e.getMessage()));
        }

        throw new ClientNotFoundException("Client with id: " + id + " was not found.");
    }

    public Client addClient(Client client) throws DatabaseException, ClientCreationException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO clients (id, accountId, firstName, lastName, email," +
                    " phoneNumber, address, city, state, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, client.getId());
            pstmt.setInt(2, client.getAcctNumber());
            pstmt.setString(3, client.getFirstName());
            pstmt.setString(4, client.getLastName());
            pstmt.setString(5, client.getEmail());
            pstmt.setString(6, client.getPhoneNumber());
            pstmt.setString(7, client.getAddress());
            pstmt.setString(8, client.getCity());
            pstmt.setString(9, client.getState());
            pstmt.setString(10, client.getZip());

            int recordsModified = pstmt.executeUpdate();

            if (recordsModified != 1) {
                throw new ClientCreationException("Unable to add account to database.");
            }

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getInt(1));
            } else {
                throw new ClientCreationException("No id generated when trying to add client. " +
                        "Client addition failed.");
            }

            return client;

        } catch (SQLException e) {
            throw new DatabaseException("Unable to connect to database: " + e.getMessage());
        }
        }

}
