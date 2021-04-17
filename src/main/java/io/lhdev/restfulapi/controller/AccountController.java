package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Account;
import io.lhdev.restfulapi.model.AccountRepository;
import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AccountController implements Controller{

    private Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }


    private Handler getAllAccounts = ctx -> {
        List<Account> listOfAccounts = accountService.getAllAccounts();
        ctx.json(listOfAccounts);
    };


    private Handler getAccountById = ctx-> {
        String id = ctx.pathParam("id");

        Account account = accountService.getAccountById(Integer.parseInt(id));

        ctx.json(account);
 };

    private Handler deleteAccountById = ctx -> {
//        String id = ctx.pathParam("id");
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/accounts", getAllAccounts);
        app.get("/accounts/:id", getAccountById);
        app.delete("/accounts/:id", deleteAccountById);
    }
}
