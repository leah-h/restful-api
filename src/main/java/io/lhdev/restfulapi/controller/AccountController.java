package io.lhdev.restfulapi.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.lhdev.restfulapi.model.Account;

import io.lhdev.restfulapi.model.Client;
import io.lhdev.restfulapi.service.AccountService;
import io.lhdev.restfulapi.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


public class AccountController implements Controller{

    private ClientService clientService;

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
        if (insertedAccount.getId() != 0) {
            ctx.json(insertedAccount);
        } else {
            logger.info("Account successfully added.");
            ctx.result("Account successfully added.");
        }

        ctx.status(201);
        ctx.json(insertedAccount);
    };

    private Handler deleteAccountById = ctx -> {

    };

    private Handler addAccountByClientId = ctx -> {
        String id = ctx.pathParam("id");
        Account account = ctx.bodyAsClass(Account.class);

        Account newAccountAdded = accountService.addAccountByClientId(Integer.parseInt(id), account);
        if (newAccountAdded.getId() != 0) {
            ctx.json(newAccountAdded);
        } else {
            logger.info("Client with id: " + Integer.parseInt(id) + " does not exist.");
            ctx.result("Client does not exist.");
        }
    };

    private Handler getAllAccountsByClientId = ctx -> {
        String clientId = ctx.pathParam("id");

        List<Account> listOfAccounts = accountService.getAllAccountsByClientId(Integer.parseInt(clientId));

        if(!listOfAccounts.isEmpty()) {
            ctx.json(listOfAccounts);
        } else {
            logger.info("Client with id: " + Integer.parseInt(clientId) + " does not exist.");
            ctx.result("Client does not exist.");
        }
    };

    private Handler getAllAccountsByClientIdWithBalance = ctx -> {
        String clientId = ctx.pathParam("id");
        String amountLessThan = ctx.pathParam("X");
        String amountGreaterThan = ctx.pathParam("Y");

        List<Account> listOfAccounts = accountService.getAllAccountsByClientIdWithBalance(Integer.parseInt(clientId),
                Integer.parseInt(amountLessThan), Integer.parseInt(amountGreaterThan));

        if(!listOfAccounts.isEmpty()){
            ctx.json(listOfAccounts);
        } else {
            logger.info("Client with id: " + Integer.parseInt(clientId) + " does not exist.");
            ctx.result("Client does not exist.");
        }
    };

    private Handler getAccountByIdForClientId = ctx -> {
        String acctId = ctx.pathParam("acctId");
        String clientId = ctx.pathParam("clientId");

        Account accountFound = accountService.getAccountByIdForClientId(Integer.parseInt(acctId),
                Integer.parseInt(clientId));

        if(accountFound.getId() != 0){
            ctx.json(accountFound);
        } else {
            logger.info("No such account for client");
            ctx.result("No such account for client.");
        }
    };

    private Handler updateAccountByIdForClientId = ctx -> {
        String acctId = ctx.pathParam("acctId");
        String clientId = ctx.pathParam("clientId");

        Account account = ctx.bodyAsClass(Account.class);

        Account accountUpdated = accountService.updateAccountByIdForClientId(Integer.parseInt(acctId),
                Integer.parseInt(clientId), account);

        Account accountCheck = accountService.getAccountById(Integer.parseInt(acctId));

        if ( accountCheck.getClientId() == Integer.parseInt(clientId)) {

            ctx.result("Account successfully updated.");

        } else {
            logger.info("No such account for client");
            ctx.result("No such account for client.");
        }
    };

    private Handler deleteAccountByIdForClientId = ctx -> {
        String acctId = ctx.pathParam("acctId");
        String clientId = ctx.pathParam("clientId");

        Account account = ctx.bodyAsClass(Account.class);

        Account accountDeleted = accountService.deleteAccountByIdForClientId(Integer.parseInt(acctId),
                Integer.parseInt(clientId), account);

        if( (accountDeleted.getId() == Integer.parseInt(acctId)) &
                (accountDeleted.getClientId() == Integer.parseInt(clientId))) {
            ctx.result("The account has been deleted: ");
            ctx.json(accountDeleted);
        } else {
            logger.info("No such account for client");
            ctx.result("No such account for client.");
        }


    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/accounts", getAllAccounts);
        app.get("/accounts/:id", getAccountById);
        app.delete("/accounts?:id", deleteAccountById);
        app.post("/accounts", addAccount);
        app.post("/clients/:id/accounts", addAccountByClientId);
        app.get("/clients/:id/accounts", getAllAccountsByClientId);
        app.get("/clients/:id/accounts?amountLessThan=X&amountGreaterThan=Y", getAllAccountsByClientIdWithBalance);
        app.get("/clients/:clientId/accounts/:acctId", getAccountByIdForClientId);
        app.put("/clients/:clientId/accounts/:acctId", updateAccountByIdForClientId);
        app.delete("/clients/:clientId/accounts/:acctId", deleteAccountByIdForClientId);
    }
}
