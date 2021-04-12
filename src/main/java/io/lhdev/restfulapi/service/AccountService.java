package io.lhdev.restfulapi.service;

import io.lhdev.restfulapi.model.Account;
import io.lhdev.restfulapi.model.AccountRepository;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService() {
        this.accountRepository = new AccountRepository();
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public Account getAccountById(String stringId) {
        Long id = Long.parseLong(stringId);
         return accountRepository.getAccountById(id);
    }
}

