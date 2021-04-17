package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Account;
import io.lhdev.restfulapi.model.AccountRepository;
import io.lhdev.restfulapi.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class AccountService {

    private AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    // For passing in the mocked accountRepository for unit testing
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public List<Account> getAllAccounts() throws DatabaseException {

        try {
            Connection connection = ConnectionUtil.getConnection();
            this.accountRepository.setConnection(connection);
            connection.setAutoCommit(false);


        } catch (SQLException e){
            throw new DatabaseException("Unable to connect" + e.getMessage());
        }
        return accountRepository.getAllAccounts();
    }
}

