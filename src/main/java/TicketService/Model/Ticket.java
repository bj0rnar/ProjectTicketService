package TicketService.Model;

public class Ticket {
    private String eventId;
    private int ticketNumber;

    private String verificationCode;
    private int seatNumber;
    private double price;

    private boolean paidFor;


    public Ticket(String eventId, int ticketNumber) {
        this.eventId = eventId;
        this.ticketNumber = ticketNumber;
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

    /*
        Verification codes should not need to be unique, just random and complicated enough
        to be impossible to fake. This is not a strong solution, but good enough for the
        prototype.
     */
    private void generateVerificationCode() {
        verificationCode = String.format("%06d", (int) Math.floor(Math.random() * 100000));
    }
}