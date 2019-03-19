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
    public void createTicket(Event event, int seatNumber) {
        //If event do not have seats, then seats has unlimited tickets.
        if(event.getAreSeatsAvailable()) {
            int totalSeatNumber = event.getEventSeats().size();
            Venue.Seat seat;
            if (event.isSeatAvailable(seatNumber)) {
                if(seatNumber == 0) {
                    seatNumber = event.getEventSeats().size()-1;
                }
                Ticket ticket = new Ticket(event);
                ticket.setSeat(event.popSeatFromEventSeatList(seatNumber));
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

    public int calculatedTotalPrice() {
        int totalPrice = 0;
        for(Ticket tickets : tickets) {
            totalPrice += tickets.getPrice();
        }
        return totalPrice;
    }

    public void buyAllTickets(long accountNumber, int cvs, Customer customer) {
        if(Bank.PayTotalPrice(accountNumber, cvs, calculatedTotalPrice())) {
            giveTicketToCustomer(customer);
        }
    }


    /**
     * Process is canceled and the seat reserved
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
