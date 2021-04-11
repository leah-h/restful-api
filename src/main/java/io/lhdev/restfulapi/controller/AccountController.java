package io.lhdev.restfulapi.controller;

import io.lhdev.restfulapi.model.Account;

import java.util.Arrays;
import java.util.List;

public class AccountController {

    public List<Account> getAllAccounts(){
        List<Account> accounts = Arrays.asList(
                new Account(5432L,
                        1L,
                        5000.00),
                new Account(6789L,
                        1L,
                        556.00)
        );
        return accounts;
    }

}
