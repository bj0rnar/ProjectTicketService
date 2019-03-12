package TicketService.Model.User;


import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Util.VisaVerification;

/*
    TODO: Make UserOld abstract parent class for Customer, Admin, Organiser?
 */
public class UserOld {
    private Ticket ticket;
    private Event event;

    private double ticketPriceTotal = 0.0;


    //  TODO: Rewrite this monstrosity, it makes me sad
    public boolean payForSelectedTicket(long cardNumber, int ccv2) {
        if (ticket == null) {
            return false;   // Throw exception instead?
        }

        if (!VisaVerification.verifyCardNumber(cardNumber) || !VisaVerification.verifyCcv2(ccv2)) {
            return false;
        }

        /*if (ticketPrice >= 500) {
            //  Everyone's poor for testing purposes
            return false;
        }*/

        ticket.setPaidFor(true);
        event.registerSoldTicket(ticket);

        return true;
    }

    public void cancelPurchase() {
        event.cancelTicket(ticket);
        ticket = null;
    }

    public Ticket receiveTicket() {
        if (ticket.isPaidFor()) {
            //  Deliver ticket - print a receipt or something.
            return ticket;
        } else {
            return null;
        }
    }



//region SETTERS
    public void setEvent(Event event) {
        this.event = event;
        this.ticketPriceTotal = event.getTicketPrice();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
//endregion

//region GETTERS
    public Event getEvent() {
        return event;
    }

    public Ticket getTicket() {
        return ticket;
    }


//endregion
}