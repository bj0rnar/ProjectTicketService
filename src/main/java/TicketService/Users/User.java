package TicketService.Users;

import TicketService.Model.Ticket;

import java.util.ArrayList;

public abstract class User {
    private int id;
    private String Username;
    private String Password;
    private String Firstname;
    private String Lastname;
    private String Email;

    private static int idCounter = 1;

    public User(String username, String password, String firstname, String lastname, String email) {
        this.id = idCounter;
        this.Username = username;
        this.Password = password;
        this.Firstname = firstname;
        this.Lastname = lastname;
        this.Email = email;

        idCounter++;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getFullname() {
        return Firstname + " " + Lastname;
    }

    public String getEmail() {
        return Email;
    }


}
