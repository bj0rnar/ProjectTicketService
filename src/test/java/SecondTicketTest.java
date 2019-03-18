import Fakes.FakeDatabaseGateway;
import Fakes.FakeOrganiser;
import Fakes.FakeUserSession;
import TicketService.Gateway.AdminEventGateway;
import TicketService.Gateway.TicketGateway;
import TicketService.Gateway.VenueAdministrationGateway;
import TicketService.Model.Event;
import TicketService.Model.Venue;
import TicketService.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SecondTicketTest {
    private FakeUserSession session;


    /*
        TODO: Add tests to assure failure on invalid input/usage
     */

    @BeforeEach
    void setUp() {
        session = new FakeUserSession();
    }


    @Test
    @DisplayName("Can get list of venues from database")
    public void canGetAllVenuesFromDb() {
        assertEquals(session.db.getAllVenues().size(), 2);
    }

    @Test
    @DisplayName("Can get list of events from database")
    public void canGetAllEventsFromDb() {
        assertEquals(session.db.getAllEvents().size(), 4);
    }

    @Test
    @DisplayName("Can get chosen venue from database")
    public void canGetVenueFromDb() {
        assertNotNull(session.db.getVenue("1"));
    }

    @Test
    @DisplayName("Can get chosen event from database")
    public void canGetEventFromDb() {
        assertNotNull(session.db.getEvent("1"));
    }

    @Test
    @DisplayName("Can set selected event")
    public void canSelectEvent() {
        Event selectedEvent = session.db.getEvent("1");
        session.setSelectedEvent("1");

        assertEquals(selectedEvent, session.getSelectedEvent());
    }

    @Test
    @DisplayName("SEAT LOLWUT")
    public void canChooseSeatByNumber() {
        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.getTicket().setSeatNumber(5);

        assertEquals(user.getTicket().getSeatNumber(), 5);*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("MOAR SEET")
    public void confirmChosenSeatIsReserved() {
        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.getEvent().reserveSeat(5, user.getTicket());

        assertEquals(1, EventDatabaseGateway.getEventFromDatabase(1).getSeats()[5]);*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("Ticket reserved for session")
    public void canReserveTicket() {
        fail("NO!");
    }

    @Test
    @DisplayName("Can create ticket")
    public void canCreateTicket() {
        session.setSelectedEvent("1");
        session.reserveTicketForEvent();

        assertNotNull(session.getTicket());
    }

    @Test
    @DisplayName("NOPE")
    public void confirmTicketReservedForEvent() {
        /*int initialNumberOfTickets;
        int expectedNewNumberOfTickets;

        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        initialNumberOfTickets = user.getEvent().getRemainingTickets();

        user.getEvent().reserveTicketForEvent();
        expectedNewNumberOfTickets = initialNumberOfTickets - 1;

        assertEquals(expectedNewNumberOfTickets, user.getEvent().getRemainingTickets());*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("Can get list of extras for selected event")
    public void canGetListOfExtras() {
        session.setSelectedEvent("4");

        assertNotNull(session.listExtras());
        assertEquals(session.listExtras().size(), 2);
    }

    @Test
    @DisplayName("NOPE")
    public void canPayForTicket() {
        //  Somewhat misleading name - only validates input format, doesn't validate card
        //  Not really a critical component, more relevant for UI
        //  Need practice with testing, though

        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        user.payForSelectedTicket(4444555544448888L, 123);

        assertTrue(user.getTicket().isPaidFor());*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("NOPE")
    public void validTicketHasVerificationCode() {
        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(user.receiveTicket().getVerificationCode());*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("NOPE")
    public void canCancelPurchase() {
        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        int ticketId = user.getTicket().ticketAvailable();

        user.cancelPurchase();

        assertNull(user.getEvent().getReservedTickets()[ticketId]);*/

        fail("Not implemented");
    }

    @Test
    @DisplayName("NOPE")
    public void canGetRefund() {
        // Not implemented yet

        fail("Not implemented");
    }

    @Test
    @DisplayName("NOPE")
    public void canReceiveTicketOnCompletedPurchase() {
        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(user.receiveTicket());*/

        fail("Not implemented");
    }
}

