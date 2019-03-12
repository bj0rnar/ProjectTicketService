package TicketService.Model.User;

import TicketService.Model.Event;
import TicketService.Model.Ticket;

import java.util.ArrayList;

public class Customer extends User{
    public ArrayList<Ticket> ticketList = new ArrayList<>();
    private Ticket ticket;
    private Event event;

    public Customer(int id, String firstName, String lastName, String email, String phoneNumber){
        super(id, firstName, lastName, email, phoneNumber);
    }

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

    public int getId(){
      return super.id;
    }
}
