import TicketService.DataAccess.FakeDB;
import TicketService.DataAccess.IRepository;
import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
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
    public void customerCanGetTicket() throws IllegalTicketCreationException {
        Ticket ticket = new Ticket(manySeatsEvent);
        Assert.assertEquals(0, customer.getTicketList().size());
        customer.getTicketList().add(ticket);
        Assert.assertEquals(1, customer.getTicketList().size());
    }

    @Test
    public void userConstructorWorksCorrectly() {
        Customer customer = new Customer("Granada", "MyPassword","Gunnar","Kristiansen", "Gk@htomail.com");
        Assertions.assertEquals("Gunnar",customer.getFirstname());
        Assertions.assertEquals("Kristiansen",customer.getLastname());
        Assertions.assertEquals("Gunnar Kristiansen",customer.getFullname());
        Assertions.assertEquals("Gk@htomail.com",customer.getEmail());
    }

    @Test
    public void organizerCanCreateEventCorrectly() throws VenueHasNoSeatsException {
        Organizer organizer = new Organizer("Krisito", "MyPassword","Leon", "Kennedy","old@school.com");
        EventHandler eventHandler = new EventHandler(organizer);
        eventHandler.createNewSeatedEvent("Event name", manySeatsEvent.getVenue(), LocalDate.of(2019,12,12), 432);
        Assertions.assertEquals("Event name", organizer.getEvents().get(0).getName());
    }

    @Test
    public void userCanBuyMultipleTickets() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,-1);
        ticketHandler.createTicket(manySeatsEvent,-1);
        ticketHandler.createTicket(manySeatsEvent,-1);
        ticketHandler.payForTicketsWithCreditCard(123412341234L,123);
        Assertions.assertEquals(3, customer.getTicketList().size());
    }

    @Test
    public void userCanReserveASeatToEvent() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,12);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals(12,customer.getTicketList().get(0).getSeat().getSeatNumber());
    }

    @Test
    public void userLoginMethodReturnsUser(){
        IRepository repo = new FakeDB();
        Customer customer = new Customer("Egil","A","A","A","A");
        repo.uploadUser(customer);
        Customer user = (Customer)FakeDB.authUserLogin("Egil", "A");
        Assertions.assertEquals(customer, user);
    }

    @Test
    public void userRegistrationComplete() {
        IRepository repo = new FakeDB();
        int currentUserDBSize = repo.getUsers().size();
        repo.uploadUser(customer);
        Assertions.assertEquals((currentUserDBSize+1), repo.getUsers().size());
    }

    @Test
    public void preventDoubleRegistration() {
        IRepository repo = new FakeDB();
        repo.uploadUser(customer);
        Assertions.assertFalse(repo.uploadUser(customer));
    }
}
