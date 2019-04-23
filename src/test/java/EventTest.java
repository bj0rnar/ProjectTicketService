import TicketService.DataAccess.DataContext;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import javafx.collections.ObservableList;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventTest {
    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    Organizer organizer;
    TicketHandler ticketHandler;


    @BeforeEach
    public void eachStartUp() throws VenueHasNoSeatsException {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        organizer = new Organizer("MyUsername", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("MyUsernameCustomer", "MyPassword","A","B","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),100, organizer);
    }

    //ADDED
    @Test
    @DisplayName("Ticket can't be created if there are no more seats")
    public void EventCantGiveAwayTicketsIfNoMoreSeatsAvailable() {
        ticketHandler.createTicket(oneSeatEvent,0);
        ticketHandler.createTicket(oneSeatEvent,0);
        Assert.assertEquals(1,ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer();
        Assert.assertEquals(1,customer.getTicketList().size());
    }

    @Test
    @DisplayName("Reserve seat while ordering")
    public void RemoveSeatFromEventWhenTicketUnderProcess() {
        //Creates a ticket.
        ticketHandler.createTicket(manySeatsEvent,0);
        //Check if seats are n-1
        Assertions.assertEquals(99, manySeatsEvent.getEventSeats().size());
        //Adds one more ticket to handler.
        ticketHandler.createTicket(manySeatsEvent,0);
        //Seats left should now be n-2
        //Check if seats are n-2
        Assertions.assertEquals(98, manySeatsEvent.getEventSeats().size());

    }

    @Test
    @DisplayName("Un-reserve seat when order is cancelled")
    public void EventGetSeatBackIfProcessIsCanceled() {
        //Current ticket size == n
        ticketHandler.createTicket(manySeatsEvent,0);
        //Current ticket size == n-1
        Assertions.assertEquals(99, manySeatsEvent.getEventSeats().size());
        ticketHandler.cancelBuyTicketProcess();
        //Current tickets size == n after ticketProcess is canceled.
        Assertions.assertEquals(100, manySeatsEvent.getEventSeats().size());

    }

    @Test
    @DisplayName("Event can get seats from Venue")
    public void EventReceivesSeatListFromVenue() {
        Assertions.assertNotNull(manySeatsEvent.getEventSeats());
    }

    @Test
    @DisplayName("GUI stuff")
    public void EventFXListIsEqualToEventList() throws VenueHasNoSeatsException {
        DataContext.CreateEvents();
        ArrayList<Event> list = EventHandler.getEventList();
        ObservableList<Event> listFx = EventHandler.getEventListFX();

        Assertions.assertEquals(listFx.size(), list.size());
    }

    @Test
    public void EventHasCorrectDate() {
        Assertions.assertEquals(LocalDate.of(2000,1,1), oneSeatEvent.getDate());
    }
    @Test
    public void EventHasCorrectName() {
        Assertions.assertEquals("JustOneSpotLeft", oneSeatEvent.getName());
    }
    @Test
    public void EventHasCorrectVenue() throws VenueHasNoSeatsException {
        Venue venue = new Venue(256, "Dorororo");
        oneSeatEvent = new Event("JustOneSpotLeft", venue, LocalDate.of(2000,1,1), 100, organizer);
        Assertions.assertEquals(venue, oneSeatEvent.getVenue());
    }

    @DisplayName("Sjekker antall billetter går ned i Event når billett er reservert")
    @Test
    public void confirmThatTicketIsReservedOnReservation(){
        int numberOfSeats = manySeatsEvent.getEventSeats().size();
        ticketHandler.createTicket(manySeatsEvent, 4);
        Assertions.assertEquals(manySeatsEvent.getEventSeats().size(), numberOfSeats-1);
    }

    @DisplayName("Sjekker at reservert sete i TicketHandler og Event er samme objekt")
    @Test
    public void checkThatTheCorrectSeatIsReservedOnReservation(){
        Venue.Seat x = manySeatsEvent.getEventSeats().get(manySeatsEvent.getEventSeats().size()-1);
        ticketHandler.createTicket(manySeatsEvent, manySeatsEvent.getEventSeats().size()-1);
        Assertions.assertEquals(x, ticketHandler.getTickets().get(0).getSeat());
    }
}
