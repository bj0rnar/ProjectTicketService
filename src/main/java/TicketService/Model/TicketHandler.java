package TicketService.Model;

import TicketService.Users.Customer;

import java.util.ArrayList;

/**
 * Acts as a order line between Customer and Ticketss.
 */
public class TicketHandler {

    /**
     * Tickets under process.
     */
    private ArrayList<Ticket> tickets = new ArrayList<>();


    public TicketHandler() {
    }


    /**
     * @return list of processed tickets
     */
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Creates a ticket to event.
     * Handles if event has seats or not.
     * Removes a seat if Event has a seat, and gives seat to Ticket. Ticket is added to ticket list.
     * If there are no seats, a plain ticket to event will be created and added to ticket list.
     * @param event Event that ticket will point to
     */
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


    /**
     * Adds all tickets in tickets list to customer
     * @param customer
     */
    public void giveTicketToCustomer(Customer customer) {
        if(customer != null) {
            for (Ticket ticket : tickets) {
                customer.getTicketList().add(ticket);
            }
        } else {
            throw new NullPointerException("User is null");
        }
    }


    /**
     * Process is canceled and the seats reserved
     * with ticket are returned to original event.
     */
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
                System.out.println("Event no longer exists, or could not be found?");
                //Do something here, to handle exception
            }
        }
    }
}
