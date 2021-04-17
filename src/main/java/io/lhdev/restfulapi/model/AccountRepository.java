package io.lhdev.restfulapi.model;

import io.lhdev.restfulapi.exceptions.AccountNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountRepository {

    private Connection connection;

    public AccountRepository() {
        super();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Account> getAllAccounts() throws DatabaseException {
        List<Account> listOfAccounts = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM accounts";

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int accountId = rs.getInt("id");
                String type = rs.getNString("account_type");
                double balance = rs.getDouble("balance");
                int clientId = rs.getInt("client_id");

                Account account = new Account(accountId, type, balance, clientId);
                listOfAccounts.add(account);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Unable to connect: " + e.getMessage());
        }
        return listOfAccounts;
    }

    public Account getAccountById(int id) throws AccountNotFoundException, DatabaseException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM accounts a WHERE a.id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int accountId = rs.getInt("id");
                String type = rs.getNString("account_type");
                double balance = rs.getDouble("balance");
                int clientId = rs.getInt("client_id");

                return new Account(accountId, type, balance, clientId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error occurred with the database: " + e.getMessage());
        }

        throw new AccountNotFoundException("Account with id: " + id + " was not found.");
    }

}
