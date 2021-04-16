package io.lhdev.restfulapi.model;

import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AccountRepository {

//    private Connection connection;
//
//    public AccountRepository(){
//        super();
//    }
//
//    public void setConnection(Connection connection){
//        this.connection = connection;
//    }

//    List<Account> accounts = Arrays.asList(
//            new Account(5432,
//                    "Checking",
//                    5000.00,
//                    1),
//            new Account(6789,
//                    "Savings",
//                    556.00,
//                    2)
//    );
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
               String type = rs.getNString("type");
               Double balance = rs.getDouble("balance");
               int clientId = rs.getInt("clientId");

               Account account = new Account(accountId, type, balance, clientId);
               listOfAccounts.add(account);
           }
       } catch (SQLException e) {
           throw new DatabaseException("Unable to connect: " + e.getMessage());
       }
       return listOfAccounts;
    }


}
