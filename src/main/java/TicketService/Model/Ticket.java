package TicketService.Model;

public class Ticket {

    private static int idCounter = 1;

    private int id;
    private Event event;
    private Venue.Seat seat;

    //idCounter is a very simple ID handler. Maybe change it out with hash?
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

    public Venue.Seat getSeat() {
        return seat;
    }

    public void setSeat(Venue.Seat seat) {
        this.seat = seat;
    }
}
