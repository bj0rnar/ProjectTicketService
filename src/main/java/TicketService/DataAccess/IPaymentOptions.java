package TicketService.DataAccess;

public interface IPaymentOptions {
    boolean payWithCreditCardDetails(long accountNumber, int cvs, int totalPrice);
}
