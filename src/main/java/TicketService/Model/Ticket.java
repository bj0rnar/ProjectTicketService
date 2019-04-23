package TicketService.Model;

import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Utility.PriceCalculator;
import TicketService.Utility.ReceiptMaker;
import TicketService.Utility.VerificationCodeMaker;

public class Ticket {

    private static int idCounter = 1;

    private int id;
    private int price;
    private Event event;
    private Venue.Seat seat;
    private String receipt;
    private String verificationCode;

    //idCounter is a very simple ID handler. Maybe change it out with hash?
    public Ticket(Event event) throws IllegalTicketCreationException {
        if(event.getAvailableTickets() > 0) {
            this.event = event;
            this.id = idCounter;
            this.price = event.getTicketPrice();
            idCounter++;
            event.setAvailableTickets(event.getAvailableTickets() - 1);
            verificationCode = VerificationCodeMaker.createVerificationCode(event);
        } else {
            throw new IllegalTicketCreationException("Tried to create ticket when no more tickets available for event: " + event.getName());
        }
    }

    public String getVerificationCode() { return verificationCode; }

    public void setVerificationCode(String verificationCode) { this.verificationCode = verificationCode; }

    public void setReceipt(String receipt){ this.receipt = receipt; }

    public String getReceipt(){ return receipt; }

    public int getId() { return id; }

    public Event getEvent() { return event; }

    public Venue.Seat getSeat() { return seat; }

    public int getPrice() { return price; }

    public void setSeat(Venue.Seat seat) { this.seat = seat; }

    @Override
    public String toString() {
        return this.event.getName() + " ticket";
    }
}
