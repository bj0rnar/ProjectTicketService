package TicketService.Model;

import TicketService.Users.Customer;

import java.util.ArrayList;

public class TicketHandler {
    ArrayList<Ticket> tickets = new ArrayList<>();

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void createTicket(Event event) {
        tickets.add(new Ticket(event));
    }

    public void giveTicketToCustomer(Customer customer) {
        for (Ticket ticket : tickets) {
            customer.getTicketList().add(ticket);
        }
    }
}
