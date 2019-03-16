import TicketService.Model.*;
import TicketService.Users.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {
    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;


    @BeforeEach
    public void eachStartUp() {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        ticketHandler = new TicketHandler();
        customer = new Customer("A","B","A@B.COM");
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),true);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),true);

    }

    @Test
    public void EventCantGiveAwayTicketsIfNoMoreSeatsAvailable() {
        ticketHandler.createTicket(oneSeatEvent);
        ticketHandler.createTicket(oneSeatEvent);
        Assert.assertEquals(1,ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1,customer.getTicketList().size());
    }

    @Test
    public void RemoveSeatFromEventWhenTicketUnderProcess() {
        //Creates a ticket.
        ticketHandler.createTicket(manySeatsEvent);
        //Check if seats are n-1
        Assertions.assertEquals(99, manySeatsEvent.getEventSeats().size());
        //Adds one more ticket to handler.
        ticketHandler.createTicket(manySeatsEvent);
        //Seats left should now be n-2
        //Check if seats are n-2
        Assertions.assertEquals(98, manySeatsEvent.getEventSeats().size());

    }

    @Test
    public void EventGeatSeatBackIfProcessIsCanceled() {

        //Current ticket size == n
        ticketHandler.createTicket(manySeatsEvent);
        //Current ticket size == n-1
        Assertions.assertEquals(99, manySeatsEvent.getEventSeats().size());
        ticketHandler.cancelBuyTicketProcess();
        //Current tickets size == n after ticketProcess is canceled.
        Assertions.assertEquals(100, manySeatsEvent.getEventSeats().size());

    }

    @Test
    public void EventReceivesSeatListFromVenue() {
        Assertions.assertNotNull(manySeatsEvent.getEventSeats());
    }
}
