package TicketService.Model;

public class Seat{
    private Ticket ticket;
    private int seatNumber;

    public Seat(int seatNumber){
        this.seatNumber = seatNumber;
    }

    public boolean isTaken(){
        return ticket != null;
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}