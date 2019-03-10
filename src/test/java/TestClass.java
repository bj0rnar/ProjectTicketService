import TicketService.Model.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestClass {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
        EventHandler.eventList.add(new Event("TG20", Venue.venues.get(0)));
    }
    @Test
    public void TestCheck(){ }












}
