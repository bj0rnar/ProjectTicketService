package TicketService.Model;

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
    public Ticket(Event event) {
        this.event = event;
        this.id = idCounter;
        this.price = event.getTicketPrice();
        idCounter++;
        verificationCode = VerificationCodeMaker.createVerificationCode(event);
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


}
