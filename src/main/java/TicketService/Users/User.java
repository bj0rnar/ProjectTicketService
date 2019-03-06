package TicketService.Users;

public class User {
    int id;
    String Firstname;
    String Lastname;
    String Email;

    static int idCounter = 1;

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

    public String getEmail() {
        return Email;
    }

    public static int getIdCounter() {
        return idCounter;
    }
}
