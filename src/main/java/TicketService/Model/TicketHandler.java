package TicketService.Model;

import TicketService.Users.Customer;

import java.util.ArrayList;

public class TicketHandler {

    private ArrayList<Ticket> tickets = new ArrayList<>();


    public TicketHandler() {
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void createTicket(Event event) {
        int seatNumber = event.getVenue().getSeats().size();
        if(event.getAreSeatsAvailable()) {
            if (seatNumber != 0) {
                event.getVenue().getSeats().get(seatNumber - 1);
                event.getVenue().getSeats().remove(seatNumber - 1);
            } else {
                System.out.println("No more seats available for event: " + event.getName());
                return;
            }
        }
        tickets.add(new Ticket(event));
    }

    public void giveTicketToCustomer(Customer customer) {
        for (Ticket ticket : tickets) {
            customer.getTicketList().add(ticket);
        }
    }
}
