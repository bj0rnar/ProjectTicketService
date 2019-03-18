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
    //private Fakes.FakeDatabaseGateway db;
    //private Fakes.FakeOrganiser organiser;
    private FakeUserSession session;


    /*
        TODO: Add tests to assure failure on invalid input/usage
     */

    @BeforeEach
    void setUp() {


        //db = new FakeDatabaseGateway();
        //organiser = new FakeOrganiser();
        session = new FakeUserSession();

        //organiser.venueAdministrationGateway = new VenueAdministrationGateway();
        //organiser.adminEventGateway = new AdminEventGateway();
        //organiser.ticketGateway = new TicketGateway();

        //Venue venue1 = organiser.venueAdministrationGateway.createUnseatedVenue("1", "Venue 1");
        //Venue venue2 = organiser.venueAdministrationGateway.createUnseatedVenue("2", "Venue 2");

        //db.addVenue(venue1);
        //db.addVenue(venue2);

//        Event event1 = organiser.adminEventGateway.createBasicEvent("1", "Test1", venue1.getVenueId(), 50, 100.0);
//        Event event2 = organiser.adminEventGateway.createBasicEvent("2", "Test2", venue1.getVenueId(), 20, 50.0);
//        Event event3 = organiser.adminEventGateway.createBasicEvent("3", "Test3", venue2.getVenueId(), 60, 300.0);
//
//        db.addEvent(event1);
//        db.addEvent(event2);
//        db.addEvent(event3);

        //event1.setNumberOfSeats(50);
        //event2.setNumberOfSeats(20);
        //event3.setNumberOfSeats(20);
    }


    @Test
    @DisplayName("Can get list of venues from database")
    public void canGetAllVenuesFromDb() {
        /*Venue venue = organiser.venueAdministrationGateway.createUnseatedVenue("3", "Venue 3");
        db.addVenue(venue);*/

        assertEquals(session.db.getAllVenues().size(), 2);
    }

    /*@Test
    @DisplayName("Can get list of events from database")
    public void canGetAllEventsFromDb() {
        assertEquals(db.getAllEvents().size(), 3);
    }*/

    /*@Test
    @DisplayName("Can get chosen venue from database")
    public void canGetVenueFromDb() {
        assertNotNull(db.getVenue("1"));
    }*/

    /*@Test
    @DisplayName("Can get chosen event from database")
    public void canGetEventFromDb() {
        assertNotNull(db.getEvent("1"));
    }*/

    /*@Test
    @DisplayName("Can set selected event")
    public void canSelectEvent() {
        Event selectedEvent = db.getEvent("1");
        session.setSelectedEvent(selectedEvent);

        assertEquals(selectedEvent, session.getSelectedEvent());
    }*/

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



        /*user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        assertNotNull(user.getTicket());*/

        fail("Not implemented");
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

    /*@Test
    @DisplayName("NONONO")
    public void canGetListOfExtras() {
        Event event = db.getEvent("1");
        organiser.adminEventGateway.addExtra(event, "Stuff", 9001.00);
        organiser.adminEventGateway.addExtra(event, "Other stuff", 2.00);
        session.setSelectedEvent(event);

        assertEquals(session.getSelectedEvent().getExtras().size(), 2);

        *//*event1.addExtra(100, "test");
        assertNotNull(event1.getExtras().get(0));*//*

        fail("Not implemented");
    }*/

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

        int ticketId = user.getTicket().getTicketNumber();

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

