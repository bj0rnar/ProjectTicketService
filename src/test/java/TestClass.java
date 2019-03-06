import TicketService.Model.Ticket;
import TicketService.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestClass {

    private User customer;
    private Ticket ticket;


    @Test
    public void TestCheck(){ }

    @Test
    public void EachTicketGetsUniqueIdWhenTicketIsCreated(){
        Ticket ticket = new Ticket();
        Assert.assertEquals(1, ticket.getId());
        Ticket secondTicket = new Ticket();
        Assert.assertEquals(2, secondTicket.getId());
        Ticket thirdTicket = new Ticket();
        Assert.assertEquals(3, thirdTicket.getId());
    }

    @Test
    public void UserCanGetTicket(){
        Assert.assertTrue(false); // TODO
    }
    @Test
    public void EachUserGetsUniqueIdWhenUserIsCreated(){
        User user = new User();
        Assert.assertEquals(1, user.getId());
        User secondUser = new User();
        Assert.assertEquals(2, secondUser.getId());
        User thirdUser = new User();
        Assert.assertEquals(3, thirdUser.getId());

    }
}
