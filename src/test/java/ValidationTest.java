import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Utility.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ValidationTest {


    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;
    Validator validator;

    @BeforeEach
    public void setUp(){
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        Organizer organizer = new Organizer("TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("A","B","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100,true, organizer);
        manySeatsEvent = new Event("manySeatedEvent", manySpotVenue, LocalDate.of(2000,1,1),250,true, organizer);
    }

    @Test
    public void verificationCodeIsMade(){
        Assertions.assertEquals(0, manySeatsEvent.getVerificationCodeList().size());
        ticketHandler.createTicket(manySeatsEvent, 2);
        Assertions.assertEquals(1, manySeatsEvent.getVerificationCodeList().size());
    }

    @Test
    public void verificationCodeIsAccepted(){
        ticketHandler.createTicket(oneSeatEvent, 0);
        Assertions.assertEquals(ticketHandler.getTickets().get(0).getVerificationCode(), oneSeatEvent.getVerificationCodeList().get(0));
    }

    @Test
    public void validatorWorksAsIntended(){
        ticketHandler.createTicket(manySeatsEvent, 4);
        int index = ticketHandler.getTickets().size();
        --index;
        Assertions.assertTrue(ticketHandler.validateTicket(ticketHandler.getTickets().get(index), manySeatsEvent));
        Assertions.assertFalse(ticketHandler.validateTicket(ticketHandler.getTickets().get(index), oneSeatEvent));
    }

    @Test
    public void wat(){
        ticketHandler.createTicket(manySeatsEvent, 44);
        System.out.println(ticketHandler.getTickets().get(0).getVerificationCode());
    }
}
