import TicketService.Model.EventHandler;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.BeforeClass;
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
        User user = new User();
        idChecker = user.getId();
        Assert.assertEquals(idChecker, user.getId());
        User secondUser = new User();
        idChecker++;
        Assert.assertEquals(idChecker, secondUser.getId());
        Customer firstCustomer = new Customer();
        idChecker++;
        Assert.assertEquals(idChecker, firstCustomer.getId());

    }

    @Test
    public void UserCanGetTicket(){
        Customer customer = new Customer();
        Ticket ticket = new Ticket(EventHandler.getEventList().get(0));
        Assert.assertEquals(0, customer.getTicketList().size());
        customer.getTicketList().add(ticket);
        Assert.assertEquals(1, customer.getTicketList().size());
    }
}
