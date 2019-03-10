import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class VenueTest {

    @Test
    public void VenueHasCorrectAmountOfSeats() {
        Venue venue = new Venue(10,"TG12");
        Assert.assertEquals(10,venue.getSeats().size());

    }
    @Test
    public void SeatsHasCorrectSeatNumber() {
        Venue venue = new Venue(10,"TG11");
        Assert.assertEquals(1,venue.getSeats().get(0).getSeatNumber());
        Assert.assertEquals(10,venue.getSeats().get(9).getSeatNumber());

    }
}
