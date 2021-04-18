package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Account;

import io.lhdev.restfulapi.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


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

    private Handler addAccount = ctx -> {
        Account account = ctx.bodyAsClass(Account.class);

        Account insertedAccount = accountService.addAccount(account);

        ctx.status(201);
        ctx.json(insertedAccount);
    };

    private Handler deleteAccountById = ctx -> {
//        String id = ctx.pathParam("id");
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/accounts", getAllAccounts);
        app.get("/accounts/:id", getAccountById);
        app.delete("/accounts/:id", deleteAccountById);
        app.post("/accounts", addAccount);
    }
}
