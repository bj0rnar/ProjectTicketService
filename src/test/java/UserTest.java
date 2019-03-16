import TicketService.Model.EventHandler;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
    }

    @Test
    public void EachUserGetsUniqueIdWhenUserIsCreated(){
        int idChecker;
        Customer customer = new Customer("A","B","A@B.COM");
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
        Customer customer = new Customer("A","B","A@B.COM");
        Ticket ticket = new Ticket(EventHandler.getEventList().get(0));
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
