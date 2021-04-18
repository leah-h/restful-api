package io.lhdev.restfulapi.dao;

import io.lhdev.restfulapi.exceptions.AccountCreationException;
import io.lhdev.restfulapi.exceptions.AccountNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Account;
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

    public Account addAccount(Account account) throws DatabaseException, AccountCreationException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO accounts (id, account_type, balance, client_id) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, account.getId());
            pstmt.setString(2, account.getType());
            pstmt.setInt(3, (int) account.getBalance());
            pstmt.setInt(4, account.getClientId());

            int recordsModified = pstmt.executeUpdate();

            if (recordsModified !=1){
                throw new AccountCreationException("Unable to add account to database");
            }

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()){
                account.setId(generatedKeys.getInt(1));
            } else {
                throw new AccountCreationException("No id generated when trying to add account. Account addition failed.");
            }

            return account;


        } catch (SQLException e) {
            throw new DatabaseException("Unable to connect to database: " + e.getMessage());
        }
    }

}
