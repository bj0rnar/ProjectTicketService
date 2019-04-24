package TicketService.Utility;

import TicketService.Model.Event;

import java.util.Random;

public class VerificationCodeMaker {
    public static String createVerificationCode(Event e){
        return randomGenerator();
    }

    private static String randomGenerator(){
        String ACCEPTABLELETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random r = new Random();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < 10; i++){
            s.append(ACCEPTABLELETTERS.charAt(r.nextInt(ACCEPTABLELETTERS.length())));
        }
        return s.toString();
    }
}
