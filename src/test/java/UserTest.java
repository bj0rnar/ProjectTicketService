import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserTest {

    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;

    @BeforeEach
    public void eachStartUp() {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        ticketHandler = new TicketHandler();
        customer = new Customer("Jon","Doe","A@B.COM");
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),true);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),true);

    }

    @Test
    public void EachUserGetsUniqueIdWhenUserIsCreated(){
        int idChecker;
        idChecker = customer.getId();
        Assert.assertEquals(idChecker, customer.getId());
        Customer secondUser = new Customer("A","B","A@B.COM");
        idChecker++;
        Assert.assertEquals(idChecker, secondUser.getId());
        Customer firstCustomer = new Customer("A","B","A@B.COM");
        idChecker++;
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
    public void UserCreationWorksCorrectly() {
        Customer customer = new Customer("Gunnar","Kristiansen", "Gk@htomail.com");
        Assertions.assertEquals("Gunnar",customer.getFirstname());
        Assertions.assertEquals("Kristiansen",customer.getLastname());
        Assertions.assertEquals("Gunnar Kristiansen",customer.getFullname());
        Assertions.assertEquals("Gk@htomail.com",customer.getEmail());
    }
}
