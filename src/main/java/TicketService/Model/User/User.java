package TicketService.Model.User;

public abstract class User {
    protected int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public User(){

    }

    public User(int id, String firstName, String lastName, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
