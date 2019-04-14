import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IntegrationTest {
    
    @Test
    public void BuyTicketProcessLimitedSeats() {
        Customer customer = new Customer("Baislo", "MyPassword","Jon", "Doe", "Jond@mail.com");
        Assertions.assertEquals("Jon Doe", customer.getFullname());

        Organizer organizer = new Organizer("UserBibi", "MyPassword","Kimmi", "Ludde","Wow@wowi.com");
        Assertions.assertEquals("Wow@wowi.com", organizer.getEmail());

        Venue telenorArena = new Venue(123,"Telenor Arena");
        Assertions.assertEquals(123, telenorArena.getSeats().size());

        organizer.createEvent("ESport 2019", telenorArena, LocalDate.of(2019, 12, 12),100,true);
        Event eSportEvent = organizer.getEvents().get(0);
        Assertions.assertTrue(eSportEvent.getAreSeatsAvailable());

        TicketHandler ticketHandler = new TicketHandler(customer);
        ticketHandler.createTicket(eSportEvent,0);
        Assertions.assertEquals(1, ticketHandler.getTickets().size());

        ticketHandler.buyAllTickets(123123123123L, 123);
        Assertions.assertEquals(1,customer.getTicketList().size());
    }
}
