package io.lhdev.restfulapi.dao;

import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
