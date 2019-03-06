package TicketService.Model;

public class Ticket {

    private static int idCounter = 1;

    private int id;
    Event event;

    public Ticket() {
        this.id = idCounter;
        idCounter++;
    }
    public Ticket(Event event) {
        this.event = event;
        this.id = idCounter;
        idCounter++;
    }

    public int getId() {
        return id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
