import TicketService.Model.*;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EventTest {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
    }

    @Test
    public void EventCantGiveAwayTicketsIfNoMoreSeatsAvailable() {
        TicketHandler ticketHandler = new TicketHandler();
        Customer customer = new Customer();
        ticketHandler.createTicket(EventHandler.eventList.get(1));
        ticketHandler.createTicket(EventHandler.eventList.get(1));
        Assert.assertEquals(1,ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1,customer.getTicketList().size());

    }
}
