package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.exceptions.AccountNotFoundException;
import io.lhdev.restfulapi.exceptions.DatabaseException;
import io.lhdev.restfulapi.model.Account;

import io.lhdev.restfulapi.model.AccountRepository;

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
        return accountRepository.getAllAccounts();
    }

    public Account getAccountById(int id) throws AccountNotFoundException, DatabaseException{
        return accountRepository.getAccountById(id);
    }
}

