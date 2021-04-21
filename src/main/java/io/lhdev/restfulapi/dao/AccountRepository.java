package io.lhdev.restfulapi.dao;

import io.lhdev.restfulapi.exceptions.AccountCreationException;
import io.lhdev.restfulapi.exceptions.AccountNotFoundException;
import io.lhdev.restfulapi.exceptions.ClientNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Account;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class AccountRepository {

    private Connection connection;
    private ClientRepository clientRepository;

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
            String sql = "INSERT INTO accounts (account_type, balance, client_id) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, account.getType());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setInt(3, account.getClientId());

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

    public void addAccountByClientId(int clientId, Account account) throws ClientNotFoundException,
            DatabaseException, AccountCreationException {
        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "INSERT INTO accounts (account_type, balance, client_id)" +
                    "VALUES (?, ?, ?)";


            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, account.getType());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setInt(3, account.getClientId());

            int recordsAdded = pstmt.executeUpdate();

            if (recordsAdded != 1) {
                throw new AccountCreationException("No new account created.");
            }

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                account.setId(generatedKeys.getInt(1));
            } else {
                throw new AccountCreationException("No id generated when trying to add account. Account addition failed.");
            }
            System.out.println("Account successfully added.");

        } catch (SQLException | AccountCreationException e) {
            e.printStackTrace();
        }

    }

}
