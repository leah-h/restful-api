package io.lhdev.restfulapi.exceptions;

public class AccountNotFoundException extends Exception{

    public AccountNotFoundException(){
        super();
    }

    public AccountNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writeableStackTrace){
        super(message, cause, enableSuppression, writeableStackTrace);
    }

    public AccountNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(Throwable cause){
        super(cause);
    }
}
