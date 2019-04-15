package TicketService.DataAccess;

public class PayPalConnection implements IPaymentOptions{

    private static boolean payPalDoWhateverPayPalDoes(long accountNumber, int cvs, int totalPrice){
        return true;
    }

    @Override
    public boolean payWithCreditCardDetails(long accountNumber, int cvs, int totalPrice) {
        return payPalDoWhateverPayPalDoes(accountNumber, cvs, totalPrice);
    }
}
