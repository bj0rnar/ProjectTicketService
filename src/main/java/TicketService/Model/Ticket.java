package TicketService.Model;

public class Ticket {

    private static int idCounter = 1;

    private int id;

    public Ticket() {
        this.id = idCounter;
        idCounter++;
    }

    public int getId() {
        return id;
    }
}
