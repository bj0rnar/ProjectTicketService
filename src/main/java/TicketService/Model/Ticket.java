package TicketService.Model;

public class Ticket {

    private static int idCounter = 1;

    private int id;
    private int price;
    private Event event;
    private Venue.Seat seat;

    //idCounter is a very simple ID handler. Maybe change it out with hash?
    public Ticket(Event event) {
        this.event = event;
        this.id = idCounter;
        this.price = event.getTicketPrice();
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

    public int getPrice() {
        return price;
    }

    public void setSeat(Venue.Seat seat) {
        this.seat = seat;
    }
}
