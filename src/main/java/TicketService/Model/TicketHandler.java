package TicketService.Model;

import TicketService.DataAccess.Bank;
import TicketService.Users.Customer;
import TicketService.Utility.PriceCalculator;
import TicketService.Utility.ReceiptMaker;
import TicketService.Utility.Validator;
import TicketService.Utility.VerificationCodeMaker;

import java.util.ArrayList;

/**
 * Acts as a order line between Customer and Ticketss.
 */
public class TicketHandler {

    private Customer customer;
    /**
     * TicketHandler is designed to function as a shopping cart in online stores.
     * All purchaes are stored in customers personal list (Customer TicketList)
     * and processed in "tickets" array below.
     */
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public TicketHandler(Customer customer) {
        this.customer = customer;
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
     *
     * @param event Event that ticket will point to
     */
    public void createTicket(Event event, int seatNumber) {

        if (event.getAreSeatsAvailable()) {
            checkSeatAvailability(event, seatNumber);
        } else {
            tickets.add(new Ticket(event));
        }
    }

    /**
     * Checks for available seats and specific seat
     */

    private void checkSeatAvailability(Event event, int seatNumber) {

        boolean availableSeats = event.getEventSeats().size() != 0;

        if (availableSeats && event.isSeatAvailable(seatNumber)) {
            createSeatedEventTicket(event, seatNumber);
        } else {
            System.out.println("No more seats available for event: " + event.getName());
        }
    }

    /**
     * Create ticket if seating is available and either seat is not reserved OR seating is assigned.
     */

    private void createSeatedEventTicket(Event event, int seatNumber) {
        Ticket ticket = new Ticket(event);
        seatReservation(event, seatNumber, ticket);
        tickets.add(ticket);

    }

    /**
     * seatNumber == 0 is for cases where customers are ASSIGNED seats (not selecting seats themselves)
     */

    private void seatReservation(Event event, int seatNumber, Ticket ticket) {
        if (seatNumber == 0) {
            seatNumber = event.getEventSeats().size() - 1;
        }
        ticket.setSeat(event.getEventSeats().get(seatNumber));
        event.popSeatFromEventSeatList(seatNumber);
        event.getVerificationCodeList().add(ticket.getVerificationCode());
    }

    /**
     * Adds all tickets in tickets list to customer. + Receipts
     */
    public void giveTicketToCustomer() {
        for (Ticket ticket : tickets) {
            customer.getTicketList().add(ticket);

            ticket.setReceipt(ReceiptMaker.addToReceipt(ticket, customer));
            customer.getReceiptList().add(ticket.getReceipt());
        }
        //Transaction is finished, "reset" the temporary list.
        tickets = new ArrayList<>();
    }

    /**
     * Returns a total ticket price for all tickets currently in shopping cart
     */
    public int calculatedTotalPrice() {
        return PriceCalculator.summarizePrice(tickets);
    }

    /**
     * Bank.PayTotalPrice should be stubbed/faked/mocked. Currently only returns true
     */
    public void buyAllTickets(long accountNumber, int cvs) {
        if (Bank.PayTotalPrice(accountNumber, cvs, calculatedTotalPrice())) {
            giveTicketToCustomer();
        }
    }

    public boolean validateTicket(Ticket t, Event e){
        return Validator.validateTicket(e, t);
    }

    public void printAllTickets() {
        ReceiptMaker.printAllTickets(customer.getReceiptList(), calculatedTotalPrice());
    }


    /**
     * Process is canceled and the seat reserved
     * with ticket are returned to original event.
     */
    public void cancelBuyTicketProcess() {
        if (tickets.size() != 0) {
            try {
                Event event;
                for (Ticket ticket : tickets) {
                    if (ticket.getSeat() != null) {
                        event = ticket.getEvent();
                        event.getEventSeats().add(ticket.getSeat());
                        ticket.setSeat(null);
                        ticket.setVerificationCode(null);
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("Event no longer exists, or could not be found?");
                //Do something here, to handle exception
            }
        }
    }
}
