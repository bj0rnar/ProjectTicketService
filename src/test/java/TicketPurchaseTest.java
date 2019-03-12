import TicketService.ExternalService.EventDatabaseGateway;
import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Model.User.UserOld;
import TicketService.Model.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Represents a complete session in which a userOld makes a purchase.
 *
 * All tests in this class need to be green, or something is seriously wrong.
 */
public class TicketPurchaseTest {
    private UserOld userOld;

    private Venue venue1;
    private Venue venue2;

    private Event event1;
    private Event event2;
    private Event event3;


    /*
        TODO: Add tests to assure failure on invalid input/usage
     */

    @BeforeEach
    void setUp() {
        userOld = new UserOld();

        venue1 = new Venue("1", "Venue 1");
        venue2 = new Venue("2", "Venue 2");

        EventDatabaseGateway.addVenueToDatabase(venue1);
        EventDatabaseGateway.addVenueToDatabase(venue2);

        event1 = new Event("Test1", LocalDate.now(), venue1, 50, 100.0);
        event2 = new Event("Test2", LocalDate.now(), venue1, 20, 50.0);
        event3 = new Event("Test3", LocalDate.now(), venue2, 60, 300.0);

        event1.setNumberOfSeats(50);
        event2.setNumberOfSeats(20);
        event3.setNumberOfSeats(20);

        EventDatabaseGateway.addEventToDatabase(event1);
        EventDatabaseGateway.addEventToDatabase(event2);
        EventDatabaseGateway.addEventToDatabase(event3);
    }

    @Test
    public void canSelectVenueFromDb() {
        assertNotNull(EventDatabaseGateway.getvenueFromDatabase("1"));
    }

    @Test
    public void canSelectEventFromDb() {
        assertNotNull(EventDatabaseGateway.getEventFromDatabase(1));
    }

    @Test
    public void canChooseSelectedEvent() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));

        assertNotNull(userOld.getEvent());
    }

    @Test
    public void canChooseSeatByNumber() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());
        userOld.getTicket().setSeatNumber(5);

        assertEquals(userOld.getTicket().getSeatNumber(), 5);
    }

    @Test
    public void confirmChosenSeatIsReserved() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());
        userOld.getEvent().reserveSeat(5, userOld.getTicket());

        assertEquals(1, EventDatabaseGateway.getEventFromDatabase(1).getSeats()[5]);
    }

    @Test
    public void canReserveTicket() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());

        assertNotNull(userOld.getTicket());
    }

    @Test
    public void confirmTicketReservedForEvent() {
        int initialNumberOfTickets;
        int expectedNewNumberOfTickets;

        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        initialNumberOfTickets = userOld.getEvent().getRemainingTickets();

        userOld.getEvent().reserveTicketForEvent();
        expectedNewNumberOfTickets = initialNumberOfTickets - 1;

        assertEquals(expectedNewNumberOfTickets, userOld.getEvent().getRemainingTickets());
    }

    @Test
    public void canSelectOptionWhereAvailable() {
        // John tried to implement this one
        event1.setOptionAvalible("Mat");

        Ticket ticket = new Ticket(2, event1);

        assertEquals("Mat", ticket.getEvent().getOptionAvalible());
    }

    @Test
    public void canPayForTicket() {
        //  Somewhat misleading name - only validates input format, doesn't validate card
        //  Not really a critical component, more relevant for UI
        //  Need practice with testing, though

        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());

        userOld.payForSelectedTicket(4444555544448888L, 123);

        assertTrue(userOld.getTicket().isPaidFor());
    }

    @Test
    public void validTicketHasVerificationCode() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());
        userOld.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(userOld.receiveTicket().getVerificationCode());
    }

    @Test
    public void canCancelPurchase() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());

        int ticketId = userOld.getTicket().getTicketNumber();

        userOld.cancelPurchase();

        assertNull(userOld.getEvent().getReservedTickets()[ticketId]);
    }

    @Test
    public void canGetRefund() {
        // Not implemented yet



        assertFalse(true);
    }

    @Test
    public void canReceiveTicketOnCompletedPurchase() {
        userOld.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        userOld.setTicket(userOld.getEvent().reserveTicketForEvent());
        userOld.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(userOld.receiveTicket());
    }
}