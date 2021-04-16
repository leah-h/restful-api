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
    private static AccountService accountService;

    public AccountController() {
        this.accountService = new AccountService();
    }

    List<Account> accounts = Arrays.asList(
            new Account(5432,
                    "Checking",
                    5000.00,
                    1),
            new Account(6789,
                    "Savings",
                    556.00,
                    2)
    );

    private Handler getAllAccounts = ctx -> {
        ctx.json(accounts);
    };


    public static Handler getAccountById = ctx-> {
//        int id = Integer.parseInt(Objects.requireNonNull(ctx.pathParam("id")));
//        AccountService service = AccountService.intance();
//        Account account = accountService.getAccountById();
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
