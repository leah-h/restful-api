package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Account;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.service.AccountService;


import java.util.Arrays;
import java.util.List;

public class AccountController implements Controller{

    private AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    private Handler getAllAccounts = ctx -> {
        List<Account> accounts = Arrays.asList(
                new Account(5432L,
                        1L,
                        5000.00),
                new Account(6789L,
                        345L,
                        556.00)
        );
        ctx.json(accounts);
    };

    private Handler getAccountById = ctx -> {
        String id = ctx.pathParam("id");

       Account account = accountService.getAccountById(id);

       ctx.json(account);
       ctx.status(200);
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/api/v1/accounts", getAllAccounts);
        app.get("api/v1/accounts/:id", getAccountById);
    }
}
