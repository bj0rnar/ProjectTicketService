import TicketService.Model.Ticket;
import TicketService.Users.Customer;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestClass {


    @Test
    public void TestCheck(){ }

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
        Ticket ticket = new Ticket();
        Assert.assertEquals(0, customer.getTicketList().size());
        customer.getTicketList().add(ticket);
        Assert.assertEquals(1, customer.getTicketList().size());
    }
}
