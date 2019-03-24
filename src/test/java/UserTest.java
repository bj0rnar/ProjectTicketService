import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

public class UserTest {

    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;

    @BeforeEach
    public void eachStartUp() {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        customer = new Customer("Jon","Doe","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        Organizer organizer = new Organizer("TicketService", "ServiceTicket","Ticket@service.com");
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100,true, organizer);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),100,true, organizer);

    }

    @Test
    @DisplayName("Each User gets their own ID")
    public void EachUserGetsUniqueIdWhenUserIsCreated(){
        System.out.println("**************** USER ID CHECK *****************");
        int idChecker;
        Customer firstUser = new Customer("A","B","A@B.COM");
        idChecker = firstUser.getId();
        Assert.assertEquals(idChecker, firstUser.getId());
        System.out.println(idChecker);
        Customer secondUser = new Customer("A","B","A@B.COM");
        idChecker++;
        System.out.println(idChecker);
        System.out.println(secondUser.getId());
        Assert.assertEquals(idChecker, secondUser.getId());
        Customer firstCustomer = new Customer("A","B","A@B.COM");
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
        Customer customer = new Customer("Gunnar","Kristiansen", "Gk@htomail.com");
        Assertions.assertEquals("Gunnar",customer.getFirstname());
        Assertions.assertEquals("Kristiansen",customer.getLastname());
        Assertions.assertEquals("Gunnar Kristiansen",customer.getFullname());
        Assertions.assertEquals("Gk@htomail.com",customer.getEmail());
    }

    @Test
    public void OrganizerCanCreateEventCorrectly() {
        Organizer organizer = new Organizer("Leon", "Kennedy","old@school.com");
        organizer.createEvent("Event name", manySeatsEvent.getVenue(), LocalDate.of(2019,12,12), 432, false);
        Assertions.assertEquals("Event name", organizer.getEvents().get(0).getName());
    }

    @Test
    public void UserCanBuyMultipleTickets() {
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.buyAllTickets(123412341234L,123, customer);
        Assertions.assertEquals(3, customer.getTicketList().size());
    }

    @Test
    public void UserCanReserveASeatToEvent() {
        ticketHandler.createTicket(manySeatsEvent,12);
        ticketHandler.giveTicketToCustomer(customer);
        Assertions.assertEquals(12,customer.getTicketList().get(0).getSeat().getSeatNumber());
    }
}
