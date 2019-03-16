package TicketService.Users;

import TicketService.Model.Ticket;

import java.util.ArrayList;

public abstract class User {
    private int id;
    private String Firstname;
    private String Lastname;
    private String Email;

    private static int idCounter = 1;

    public User() {
        this.id = idCounter;
        idCounter++;
    }

    public User(String firstname, String lastname, String email) {
        this.id = idCounter;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;

        idCounter++;
    }

    public int getId() {
        return id;
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

    public static int getIdCounter() {
        return idCounter;
    }

}
