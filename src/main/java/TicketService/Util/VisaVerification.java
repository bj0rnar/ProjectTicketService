package TicketService.Util;


/**
 * Helper class for payment.
 *
 * Guessing most of these methods belong in a controller in a finished product,
 * as input validation.
 *
 * I have no idea how to actually verify any of this, and writing code that can handle different solutions,
 * providers, regions etc. is way too much work. Only really needed for testing and proof-of-concept, so I'm
 * keeping it simple and defining my own rules for what constitutes valid input.
 *
 * Verifying that a card is valid and has sufficient funds is handled by the payment service(s).
 */
public class VisaVerification {

    //  Rule: Has to be a three-digit int. That's it.
    public static boolean verifyCcv2(int ccv2) {
        if (Integer.toString(ccv2).length() == 3) {
            return true;
        } else {
            return false;
        }
    }

    //  Rule: Has to be a sixteen-digit int, and start with the number 4
    //  For some reason, that translates to 52 when converted to char
    public static boolean verifyCardNumber(long cardNumber) {
        String valueAsString = Long.toString(cardNumber);
        //char firstDigit = valueAsString.charAt(0);
        //char correctFirstDigit = 4;
        //char actualFirstDigit = valueAsString.charAt(0);

        if (valueAsString.length() == 16 && valueAsString.charAt(0) == 52) {
            return true;
        } else {
            return false;
        }
    }
}