import TicketService.Model.EventHandler;
import TicketService.Model.Ticket;
import TicketService.Model.TicketHandler;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TicketTest {
    @Test
    public void EachTicketGetsUniqueIdWhenTicketIsCreated(){
        int idChecker;
        Ticket ticket = new Ticket();
        idChecker = ticket.getId();
        Assert.assertEquals(idChecker, ticket.getId());
        Ticket secondTicket = new Ticket();
        idChecker++;
        Assert.assertEquals(idChecker, secondTicket.getId());
        Ticket thirdTicket = new Ticket();
        idChecker++;
        Assert.assertEquals(idChecker, thirdTicket.getId());
    }

    @Test
    public void TicketHandlerCanReceiveTicket() {
        TicketHandler ticketHandler = new TicketHandler();
        Assert.assertEquals(0, ticketHandler.getTickets().size());
        ticketHandler.getTickets().add(new Ticket());
        Assert.assertEquals(1, ticketHandler.getTickets().size());
    }

    @Test
    public void TicketHandlerCanCreateCompleteTicketAndGiveToUser() {
        TicketHandler ticketHandler = new TicketHandler();
        ticketHandler.createTicket(EventHandler.eventList.get(0));
        Customer customer = new Customer();
        Assert.assertEquals(0, customer.getTicketList().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1, customer.getTicketList().size());
        Assert.assertEquals(EventHandler.eventList.get(0).getName(), customer.getTicketList().get(0).getEvent().getName());
    }

}
