import TicketService.Model.EventHandler;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VenueTest {

    Venue venue;

    @BeforeEach
    public void eachStartUp() {
        venue = new Venue(10,"TG12","Gata 2, Halden");

    }

    @Test
    public void VenueHasCorrectName() {
        Venue strangerNames = new Venue(10,"ABC ÆØÅæøå102!","Gata 2, Halden");
        Assertions.assertEquals("ABC ÆØÅæøå102!", strangerNames.getName());
    }

    @Test
    public void VenueWithoutSeatsCanBeCreated() {
        Venue venue = new Venue(0,"NoSeats","Gata 2, Halden");
        Assertions.assertEquals(0, venue.getSeats().size());
        Assertions.assertEquals("NoSeats", venue.getName());
    }

    @Test
    public void VenueHasCorrectAmountOfSeats() {
        Assert.assertEquals(10,venue.getSeats().size());

    }
    @Test
    public void SeatsHasCorrectSeatNumber() {
        Assert.assertEquals(0,venue.getSeats().get(0).getSeatNumber());
        Assert.assertEquals(9,venue.getSeats().get(9).getSeatNumber());
    }
}
