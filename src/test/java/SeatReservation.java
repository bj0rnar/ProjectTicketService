import TicketService.ExternalService.EventDatabaseGateway;
import TicketService.Model.Event;
import TicketService.Model.User.Customer;
import TicketService.Model.User.User;
import TicketService.Model.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class SeatReservation {

    User customer;
    Venue tinyPlace;
    Event coolEvent;

    @BeforeEach
    void upAndRunning(){
        customer = new User();

        tinyPlace = new Venue("69", "Otta kulturhus", 20);

        coolEvent = new Event(69, "Stuff", LocalDate.now(), tinyPlace, 20.0);

        EventDatabaseGateway.addEventToDatabase(coolEvent);
        EventDatabaseGateway.addVenueToDatabase(tinyPlace);


    }
    @Test
    public void checkIfReservationWorks(){

        customer.setEvent(coolEvent);
        customer.setTicket(customer.getEvent().reservePlz());
        customer.getEvent().getVenue().seatReservation(13, customer.getTicket());
        assertEquals(13, customer.getTicket().getSeatNumber());
    }

    @Test
    public void seatsAreAdded(){
        assertEquals(20, tinyPlace.getSeats().length);
        tinyPlace.addMoreSeats(1);
        assertEquals(21, tinyPlace.getSeats().length);
    }

    @Test
    public void seatsAreRemoved(){
        assertEquals(20, tinyPlace.getSeats().length);
        tinyPlace.removeSeats(10);
        assertEquals(10, tinyPlace.getSeats().length);
    }

}
