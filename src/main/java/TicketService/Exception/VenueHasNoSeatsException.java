package TicketService.Exception;


public class VenueHasNoSeatsException extends Exception {
    public VenueHasNoSeatsException(String errorMessage) {
        super(errorMessage);
    }
}
