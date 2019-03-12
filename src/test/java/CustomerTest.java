import TicketService.Model.Event;
import TicketService.Model.Ticket;
import TicketService.Model.Venue;
import TicketService.Model.User.Customer;
import TicketService.Model.CustumerHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    Customer customer1;

    @BeforeEach
    public void setup(){
        CustumerHandler custumerHandler = new CustumerHandler();

        Customer customer1 = new Customer(1, "john", "hoaas", "none", "12345678");
    }

    @Test
    public void canCreateCustomer(){
        assertEquals(1, customer1.getId());
    }
}
