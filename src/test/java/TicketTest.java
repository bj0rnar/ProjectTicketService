import TicketService.Model.EventHandler;
import TicketService.Model.Ticket;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
    }

    @Test
    public void EachTicketGetsUniqueIdWhenTicketIsCreated(){
        int idChecker;
        Ticket ticket = new Ticket(EventHandler.getEventList().get(0));
        idChecker = ticket.getId();
        Assert.assertEquals(idChecker, ticket.getId());
        Ticket secondTicket = new Ticket(EventHandler.getEventList().get(0));
        idChecker++;
        Assert.assertEquals(idChecker, secondTicket.getId());
        Ticket thirdTicket = new Ticket(EventHandler.getEventList().get(0));
        idChecker++;
        Assert.assertEquals(idChecker, thirdTicket.getId());
    }

    @Test
    public void TicketHandlerCanReceiveTicket() {
        TicketHandler ticketHandler = new TicketHandler();
        Assert.assertEquals(0, ticketHandler.getTickets().size());
        ticketHandler.getTickets().add(new Ticket(EventHandler.getEventList().get(0)));
        Assert.assertEquals(1, ticketHandler.getTickets().size());
    }

    @Test
    public void TicketCanGetSeat() {
        TicketHandler ticketHandler = new TicketHandler();
        ticketHandler.createTicket(EventHandler.getEventList().get(0));
        Assertions.assertNotNull(ticketHandler.getTickets().get(0).getSeat());
    }

    @Test
    public void TicketHandlerCanCreateCompleteTicketAndGiveToUser() {
        TicketHandler ticketHandler = new TicketHandler();
        ticketHandler.createTicket(EventHandler.getEventList().get(0));
        Customer customer = new Customer("A","B","A@B.COM");
        Assert.assertEquals(0, customer.getTicketList().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1, customer.getTicketList().size());
        Assert.assertEquals(EventHandler.getEventList().get(0).getName(), customer.getTicketList().get(0).getEvent().getName());
    }


}
