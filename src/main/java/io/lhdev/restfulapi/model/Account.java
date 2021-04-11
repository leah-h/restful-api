package io.lhdev.restfulapi.model;

public class Account {

    private Long id;
    private Long clientId;
    private double balance;

    public Account() {
        super();
    }

    public Account(Long id, Long clientId, double balance) {
        this.id = id;
        this.clientId = clientId;
        this.balance = balance;
    }

    public Account(Long clientId, double balance) {
        this.clientId = clientId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", balance=" + balance +
                '}';
    }
}
