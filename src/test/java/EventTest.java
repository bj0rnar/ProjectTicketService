import TicketService.Model.*;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EventTest {

    @BeforeAll
    public static void startUp() {
        Venue.CreateVenues();
        EventHandler.CreateEvents();
    }

    @Test
    public void EventCantGiveAwayTicketsIfNoMoreSeatsAvailable() {
        TicketHandler ticketHandler = new TicketHandler();
        Customer customer = new Customer("A","B","A@B.COM");
        ticketHandler.createTicket(EventHandler.getEventList().get(1));
        ticketHandler.createTicket(EventHandler.getEventList().get(1));
        Assert.assertEquals(1,ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1,customer.getTicketList().size());

    }

    @Test
    public void EventLoosesSeatSizeForEachTicketUnderProcess() {
        int seatsBefore, seatsAfter;
        TicketHandler ticketHandler = new TicketHandler();
        seatsBefore =  EventHandler.getEventList().get(0).getVenue().getSeats().size();
        //Creates a ticket.
        ticketHandler.createTicket(EventHandler.getEventList().get(0));
        //Seats left should now be n-1
        seatsAfter = EventHandler.getEventList().get(0).getVenue().getSeats().size();
        //Check if seats are n-1
        Assertions.assertEquals(seatsBefore-1, seatsAfter);
        //Adds one more ticket to handler.
        ticketHandler.createTicket(EventHandler.getEventList().get(0));
        //Seats left should now be n-2
        seatsAfter = EventHandler.getEventList().get(0).getVenue().getSeats().size();
        //Check if seats are n-2
        Assertions.assertEquals(seatsBefore-2, seatsAfter);

    }

    @Test
    public void EventGeatSeatBackIfProcessIsCanceled() {
        int seatsBefore, seatsAfter;
        TicketHandler ticketHandler = new TicketHandler();

        //Current ticket size == n
        seatsBefore =  EventHandler.getEventList().get(0).getVenue().getSeats().size();
        ticketHandler.createTicket(EventHandler.getEventList().get(0));
        //Current ticket size == n-1
        seatsAfter = EventHandler.getEventList().get(0).getVenue().getSeats().size();
        Assertions.assertEquals(seatsBefore-1, seatsAfter);
        ticketHandler.cancelBuyTicketProcess();
        //Current tickets size == n after ticketProcess is canceled.
        seatsAfter = EventHandler.getEventList().get(0).getVenue().getSeats().size();
        Assertions.assertEquals(seatsBefore, seatsAfter);

    }
}
