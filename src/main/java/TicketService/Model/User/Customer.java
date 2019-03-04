package TicketService.Model.User;

import TicketService.Model.Event;
import TicketService.Model.Ticket;

public class Customer {
    private Ticket ticket;
    private Event event;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
