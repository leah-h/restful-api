package io.lhdev.restfulapi.client;

public class Client {

    private Long id;
    private String firstName;
    private String lastName;
    private Long acctNumber;
    private String address;

    public Client() {
        super();
    }

    public Client(Long id, String firstName, String lastName, Long acctNumber, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.acctNumber = acctNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(Long acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", acctNumber=" + acctNumber +
                ", address='" + address + '\'' +
                '}';
    }
}
