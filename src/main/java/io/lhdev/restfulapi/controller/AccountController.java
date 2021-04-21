package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.dao.ClientRepository;
import io.lhdev.restfulapi.exceptions.ClientNotFoundException;
import io.lhdev.restfulapi.model.Account;

import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class AccountController implements Controller{

    private ClientRepository clientRepository;

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

    };

    private Handler addAccountByClientId = ctx -> {
        String id = ctx.pathParam("id");
        Account account = ctx.bodyAsClass(Account.class);

        accountService.addAccountByClientId(Integer.parseInt(id), account);

        ctx.result("New account successfully added.");
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/accounts", getAllAccounts);
        app.get("/accounts/:id", getAccountById);
        app.delete("/accounts?:id", deleteAccountById);
        app.post("/accounts", addAccount);
        app.post("/clients/:id/accounts", addAccountByClientId);
    }
}
