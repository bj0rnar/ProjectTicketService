import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class UserTest {

    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;

    @BeforeEach
    public void eachStartUp() throws VenueHasNoSeatsException {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        customer = new Customer("Arnoldsen", "MyPassword","Jon","Doe","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        Organizer organizer = new Organizer("Knutsen", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),100, organizer);

    }

    @Test
    @DisplayName("Each User gets their own ID")
    public void EachUserGetsUniqueIdWhenUserIsCreated(){
        System.out.println("**************** USER ID CHECK *****************");
        int idChecker;
        Customer firstUser = new Customer("Fredrik", "MyPassword","A","B","A@B.COM");
        idChecker = firstUser.getId();
        Assert.assertEquals(idChecker, firstUser.getId());
        System.out.println(idChecker);
        Customer secondUser = new Customer("Kjetil", "MyPassword","A","B","A@B.COM");
        idChecker++;
        System.out.println(idChecker);
        System.out.println(secondUser.getId());
        Assert.assertEquals(idChecker, secondUser.getId());
        Customer firstCustomer = new Customer("Gunnar", "MyPassword","A","B","A@B.COM");
        idChecker++;
        System.out.println(idChecker);
        System.out.println(firstCustomer.getId());
        Assert.assertEquals(idChecker, firstCustomer.getId());
    }

    @Test
    public void UserCanGetTicket(){
        Ticket ticket = new Ticket(manySeatsEvent);
        Assert.assertEquals(0, customer.getTicketList().size());
        customer.getTicketList().add(ticket);
        Assert.assertEquals(1, customer.getTicketList().size());
    }

    @Test
    public void UserConstructorWorksCorrectly() {
        Customer customer = new Customer("Granada", "MyPassword","Gunnar","Kristiansen", "Gk@htomail.com");
        Assertions.assertEquals("Gunnar",customer.getFirstname());
        Assertions.assertEquals("Kristiansen",customer.getLastname());
        Assertions.assertEquals("Gunnar Kristiansen",customer.getFullname());
        Assertions.assertEquals("Gk@htomail.com",customer.getEmail());
    }

    @Test
    public void OrganizerCanCreateEventCorrectly() throws VenueHasNoSeatsException {
        Organizer organizer = new Organizer("Krisito", "MyPassword","Leon", "Kennedy","old@school.com");
        EventHandler eventHandler = new EventHandler(organizer);
        eventHandler.createNewSeatedEvent("Event name", manySeatsEvent.getVenue(), LocalDate.of(2019,12,12), 432);
        Assertions.assertEquals("Event name", organizer.getEvents().get(0).getName());
    }

    @Test
    public void UserCanBuyMultipleTickets() {
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.payForTicketsWithCreditCard(123412341234L,123);
        Assertions.assertEquals(3, customer.getTicketList().size());
    }

    @Test
    public void UserCanReserveASeatToEvent() {
        ticketHandler.createTicket(manySeatsEvent,12);
        ticketHandler.giveTicketToCustomer();
        Assertions.assertEquals(12,customer.getTicketList().get(0).getSeat().getSeatNumber());
    }
}
