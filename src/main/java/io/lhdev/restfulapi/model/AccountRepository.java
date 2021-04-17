package io.lhdev.restfulapi.model;

import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AccountRepository {

    private Connection connection;

    public AccountRepository(){
        super();
    }

    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public List<Account> getAllAccounts() throws DatabaseException{
       List<Account> listOfAccounts = new ArrayList<>();

       try (Connection connection = ConnectionUtil.getConnection()){
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


}
