package TicketService.Exception;

public class IllegalTicketCreationException extends Exception {
    public IllegalTicketCreationException(String errorString) {
        super(errorString);
    }
}
