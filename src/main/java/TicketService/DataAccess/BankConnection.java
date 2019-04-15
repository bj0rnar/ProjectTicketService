package TicketService.DataAccess;

public class BankConnection implements IPaymentOptions {

    public static boolean PayTotalPrice(long accountNumber, int cvs, int totalPrice) {
        return true;
    }

    @Override
    public boolean payWithCreditCardDetails(long accountNumber, int cvs, int totalPrice) {
        return PayTotalPrice(accountNumber, cvs, totalPrice);
    }
}
