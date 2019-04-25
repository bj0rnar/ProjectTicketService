package TicketService.Model;

import TicketService.DataAccess.BankConnection;
import TicketService.DataAccess.IPaymentOptions;
import TicketService.DataAccess.PayPalConnection;
import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Users.Customer;
import TicketService.Utility.*;

import java.util.ArrayList;

/**
 * Acts as a order line between Customer and Ticketss.
 */
public class TicketHandler {

    private Customer customer;
    /**
     * TicketHandler is designed to function as a shopping cart in online stores.
     * All purchases are stored in customers personal list (Customer TicketList)
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
    public void createTicket(Event event) throws IllegalTicketCreationException {
        Ticket ticket = new Ticket(event);
        tickets.add(ticket);
        event.getVerificationCodeList().add(ticket.getVerificationCode());
    }

    public void createTicket(Event event, int seatNumber) throws IllegalTicketCreationException {

        boolean availableSeats = event.getEventSeats().size() != 0;

        if (availableSeats) {
            if(event.isSeatAvailable(seatNumber)) {
                Ticket ticket = new Ticket(event);
                seatReservation(event, seatNumber, ticket);
                tickets.add(ticket);
            }
            else {
                throw new IllegalTicketCreationException("Seat already in use");
            }
        } else {
            System.out.println("No more seats available for event: " + event.getName());
        }
    }


    /**
     * seatNumber == -1 is for cases where customers are ASSIGNED seats (not selecting seats themselves)
     */

    private void seatReservation(Event event, int seatNumber, Ticket ticket) throws IllegalTicketCreationException {
        if (seatNumber == -1) {
            seatNumber = event.getEventSeats().size() - 1;
        }
        ticket.setSeat(event.getEventSeats().get(seatNumber));
        event.popSeatFromEventSeatList(seatNumber);
        event.getVerificationCodeList().add(ticket.getVerificationCode());

    }

    /**
     * Adds all tickets in tickets list to customer. + Receipts
     */
    private void giveTicketToCustomer() {
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
     * BankConnection.PayTotalPrice should be stubbed/faked/mocked. Currently only returns true
     */

    public boolean payForTicketsWithCreditCard(long accountNumber, int cvs){
        IPaymentOptions bankIntermediary = new BankConnection();
        if (bankIntermediary.payWithCreditCardDetails(accountNumber, cvs, calculatedTotalPrice())) {
            giveTicketToCustomer();
            return true;
        }
        return false;
    }

    public boolean payForTicketsWithPayPal(long accountNumber, int cvs){
        IPaymentOptions payPalIntermediary = new PayPalConnection();
        if(payPalIntermediary.payWithCreditCardDetails(accountNumber, cvs, calculatedTotalPrice())){
            giveTicketToCustomer();
            return true;
        }
        return false;
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
                        event.setAvailableTickets(event.getAvailableTickets()+1);
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
