import TicketService.ExternalService.EventDatabaseGateway;
import TicketService.Model.Event;
import TicketService.Model.User.UserOld;
import TicketService.Model.Venue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class SeatReservation {

    UserOld customer;
    Venue tinyPlace;
    Event coolEvent;

    @BeforeEach
    void upAndRunning(){
        customer = new UserOld();

        tinyPlace = new Venue("69", "Otta kulturhus", 20);

        coolEvent = new Event(69, "Stuff", LocalDate.now(), tinyPlace, 20.0);

        EventDatabaseGateway.addEventToDatabase(coolEvent);
        EventDatabaseGateway.addVenueToDatabase(tinyPlace);


    }

    @Test
    public void reserveWithSeats(){
        customer.setEvent(EventDatabaseGateway.getEventFromDatabase(69));
        customer.setTicket(customer.getEvent().reserveTicketForEventWithSeating(4));
        assertEquals(4, customer.getTicket().getSeatNumber());
    }

    @Test
    public void checkRemainingSeats(){
        System.out.println(coolEvent.getRemainingTickets());
        System.out.println(coolEvent.getNumberOfTickets());
    }

    @Test
    public void databaseRespondsAsExpected(){
        customer.setEvent(EventDatabaseGateway.getEventFromDatabase(69));
        customer.setTicket(customer.getEvent().reserveTicketForEvent());
        customer.getEvent().getVenue().seatReservation(19, customer.getTicket());
        assertEquals(19, customer.getTicket().getSeatNumber());
    }


    @Test
    public void checkIfReservationWorks(){
        customer.setEvent(coolEvent);
        customer.setTicket(customer.getEvent().reserveTicketForEvent());
        customer.getEvent().getVenue().seatReservation(11, customer.getTicket());
        assertEquals(11, customer.getTicket().getSeatNumber());
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
