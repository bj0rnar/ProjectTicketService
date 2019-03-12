import TicketService.Model.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestClass {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
    }
    @Test
    public void TestCheck(){ }












}
