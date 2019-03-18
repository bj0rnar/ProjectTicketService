import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IntegrationTest {

    @Test
    public void BuyTicketProcessLimitedSeats() {
        Customer customer = new Customer("Jon", "Doe", "Jond@mail.com");
        Assertions.assertEquals("Jon Doe", customer.getFullname());
        Organizer organizer = new Organizer("Kimmi", "Ludde","Wow@wowi.com");
        Assertions.assertEquals("Wow@wowi.com", organizer.getEmail());
        Venue telenorArena = new Venue(123,"Telenor Arena");
        Assertions.assertEquals(123, telenorArena.getSeats().size());
        Event eSportEvent = new Event("ESport 2019", telenorArena, LocalDate.of(2019, 12, 12),100,true, organizer);
        Assertions.assertTrue(eSportEvent.getAreSeatsAvailable());
        TicketHandler ticketHandler = new TicketHandler();
        ticketHandler.createTicket(eSportEvent);
        Assertions.assertEquals(1, ticketHandler.getTickets().size());
        ticketHandler.buyAllTickets(123123123123L, 123, customer);
        Assertions.assertEquals(1,customer.getTicketList().size());
    }
}
