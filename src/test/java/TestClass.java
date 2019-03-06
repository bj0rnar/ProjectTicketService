import TicketService.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestClass {

    private User customer;

    @BeforeEach
    public void initialize() {
        customer = new User();
    }

    @Test
    public void emptyTest(){
        System.out.println("This test is testing");
    }

    @Test
    public void UserCanGetTicket(){
        Assert.assertFalse(true); // TODO
    }
    @Test
    public void CheckThatUserGetsUniquieID(){
        Assert.assertEquals(1, customer.getId());
        User secondUser = new User();
        Assert.assertEquals(2, secondUser.getId());
        User thirdUser = new User();
        Assert.assertEquals(3, thirdUser.getId());

    }
}
