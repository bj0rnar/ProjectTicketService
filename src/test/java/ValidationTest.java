import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.EventHandler;
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
    EventHandler eventHandler;

    @BeforeEach
    public void setUp() throws VenueHasNoSeatsException {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        Organizer organizer = new Organizer("BrukernavnetErIBruk", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        eventHandler = new EventHandler(organizer);
        customer = new Customer("BrukernavnetErIkkeIBruk", "MyPassword","A","B","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        manySeatsEvent = new Event("manySeatedEvent", manySpotVenue, LocalDate.of(2000,1,1),250, organizer);
    }

    @Test
    public void verificationCodeIsMade() throws IllegalTicketCreationException {
        Assertions.assertEquals(0, manySeatsEvent.getVerificationCodeList().size());
        ticketHandler.createTicket(manySeatsEvent, 2);
        Assertions.assertEquals(1, manySeatsEvent.getVerificationCodeList().size());
    }

    @Test
    public void verificationCodeIsAccepted() throws IllegalTicketCreationException {
        ticketHandler.createTicket(oneSeatEvent);
        Assertions.assertEquals(ticketHandler.getTickets().get(0).getVerificationCode(), oneSeatEvent.getVerificationCodeList().get(0));
    }

    @Test
    public void validatorWorksAsIntended() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 4);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        int index = customer.getTicketList().size();
        --index;
        Assertions.assertTrue(eventHandler.validateTicket(customer.getTicketList().get(index).getVerificationCode(), manySeatsEvent));
        Assertions.assertFalse(eventHandler.validateTicket(customer.getTicketList().get(index).getVerificationCode(), oneSeatEvent));
    }
    
    @Test
    public void cancellingRemovesVerificationCodeFromAccepted() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,1);
        int index = ticketHandler.getTickets().size();
        int index2 = manySeatsEvent.getVerificationCodeList().size();
        --index2;
        --index;
        Assertions.assertEquals(ticketHandler.getTickets().get(index).getVerificationCode(), manySeatsEvent.getVerificationCodeList().get(index2));
        ticketHandler.cancelBuyTicketProcess();
        Assertions.assertNotEquals(ticketHandler.getTickets().get(index).getVerificationCode(), manySeatsEvent.getVerificationCodeList().get(index2));
    }

    @Test
    public void multipleTicketsAreValidated() throws IllegalTicketCreationException {
        int index = customer.getTicketList().size();
        ticketHandler.createTicket(manySeatsEvent, 1);
        ticketHandler.createTicket(manySeatsEvent, 69);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertTrue(eventHandler.validateTicket(customer.getTicketList().get(index).getVerificationCode(), manySeatsEvent));
        ++index;
        Assertions.assertTrue(eventHandler.validateTicket(customer.getTicketList().get(index).getVerificationCode(), manySeatsEvent));
    }

}
