package TicketService.Model;

public class Ticket {
    private int ticketNumber;
    private Event event;



/*
    private String verificationCode;
    private int seatNumber;
    private double price;

    private boolean paidFor;


    public Ticket(int ticketNumber, Event event) {
        this.ticketNumber = ticketNumber;
        this.event = event;
        paidFor = false;
    }

    public boolean isPaidFor() {
        return paidFor;
    }

    public void setPaidFor(boolean paidFor) {
        this.paidFor = paidFor;

        if (paidFor) {
            generateVerificationCode();
        } else {
            verificationCode = null;
        }
    }

    public Event getEvent() {
        return event;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    */

    /*
        Verification codes should not need to be unique, just random and complicated enough
        to be impossible to fake. This is not a strong solution, but good enough for the
        prototype.
     */

    /*
    private void generateVerificationCode() {
        verificationCode = String.format("%06d", (int) Math.floor(Math.random() * 100000));
    } */

}