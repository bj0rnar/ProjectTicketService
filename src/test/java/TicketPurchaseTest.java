/**
 * Represents a complete session in which a user makes a purchase.
 *
 * All tests in this class need to be green, or something is seriously wrong.
 */
public class TicketPurchaseTest {
/*    private User user;

    private Venue venue1;
    private Venue venue2;

    private Event event1;
    private Event event2;
    private Event event3;


    *//*
        TODO: Add tests to assure failure on invalid input/usage
     *//*

    @BeforeEach
    void setUp() {
        user = new User();

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
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));

        assertNotNull(user.getEvent());
    }

    @Test
    public void canChooseSeatByNumber() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.getTicket().setSeatNumber(5);

        assertEquals(user.getTicket().getSeatNumber(), 5);
    }

    @Test
    public void confirmChosenSeatIsReserved() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.getEvent().reserveSeat(5, user.getTicket());

        assertEquals(1, EventDatabaseGateway.getEventFromDatabase(1).getSeats()[5]);
    }

    @Test
    public void canReserveTicket() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        assertNotNull(user.getTicket());
    }

    @Test
    public void confirmTicketReservedForEvent() {
        int initialNumberOfTickets;
        int expectedNewNumberOfTickets;

        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        initialNumberOfTickets = user.getEvent().getRemainingTickets();

        user.getEvent().reserveTicketForEvent();
        expectedNewNumberOfTickets = initialNumberOfTickets - 1;

        assertEquals(expectedNewNumberOfTickets, user.getEvent().getRemainingTickets());
    }

    @Test
    public void canSelectOptionWhereAvailable() {
        // Not implemented yet

        //assertFalse(true);

        event1.addExtra(100, "test");
        assertNotNull(event1.getExtras().get(0));
    }

    @Test
    public void canPayForTicket() {
        //  Somewhat misleading name - only validates input format, doesn't validate card
        //  Not really a critical component, more relevant for UI
        //  Need practice with testing, though

        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        user.payForSelectedTicket(4444555544448888L, 123);

        assertTrue(user.getTicket().isPaidFor());
    }

    @Test
    public void validTicketHasVerificationCode() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(user.receiveTicket().getVerificationCode());
    }

    @Test
    public void canCancelPurchase() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());

        int ticketId = user.getTicket().ticketAvailable();

        user.cancelPurchase();

        assertNull(user.getEvent().getReservedTickets()[ticketId]);
    }

    @Test
    public void canGetRefund() {
        // Not implemented yet

        assertFalse(true);
    }

    @Test
    public void canReceiveTicketOnCompletedPurchase() {
        user.setEvent(EventDatabaseGateway.getEventFromDatabase(1));
        user.setTicket(user.getEvent().reserveTicketForEvent());
        user.payForSelectedTicket(4444555544448888L, 123);

        assertNotNull(user.receiveTicket());
    }*/
}