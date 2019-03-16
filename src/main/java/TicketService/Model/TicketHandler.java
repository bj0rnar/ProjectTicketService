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
        //If event do not have seats, then seats has unlimited tickets.
        if(event.getAreSeatsAvailable()) {
            int seatNumber = event.getEventSeats().size();
            Venue.Seat seat;
            if (seatNumber != 0) {
                Ticket ticket = new Ticket(event);
                ticket.setSeat(event.getEventSeats().get(seatNumber - 1));
                event.getEventSeats().remove(seatNumber - 1);
                tickets.add(ticket);
            } else {
                System.out.println("No more seats available for event: " + event.getName());
                return;
            }
        } else {
            tickets.add(new Ticket(event));
        }
    }

    public void giveTicketToCustomer(Customer customer) {
        if(customer != null) {
            for (Ticket ticket : tickets) {
                customer.getTicketList().add(ticket);
            }
        } else {
            throw new NullPointerException("User is null");
        }
    }

    public void cancelBuyTicketProcess() {
        if(tickets.size() != 0) {
            try {
                Event event;
                for (Ticket ticket: tickets) {
                    if(ticket.getSeat() != null) {
                        event = ticket.getEvent();
                        event.getEventSeats().add(ticket.getSeat());
                        ticket.setSeat(null);
                    }
                }
            }
            catch (NullPointerException e) {
                System.out.println("Event no longer exists, or couldnt be found?");
            }
        }
    }
}
