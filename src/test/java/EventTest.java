import TicketService.DataAccess.FakeDB;
import TicketService.Exception.IllegalTicketCreationException;
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
        oneSeatEvent = new Event("OneSpotEvent", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        manySeatsEvent = new Event("ManySpotsEvent", manySpotVenue, LocalDate.of(2000,1,1),100, organizer);
    }

    //ADDED
    @Test
    @DisplayName("Ticket can't be created if there are no more seats")
    public void EventCantGiveAwayTicketsIfNoMoreSeatsAvailable() {
        try {
            ticketHandler.createTicket(oneSeatEvent,-1);
        } catch (IllegalTicketCreationException e) {
            //Ticket was not created
        }
        try {
            ticketHandler.createTicket(oneSeatEvent,-1);
        } catch (IllegalTicketCreationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1,ticketHandler.getTickets().size());
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assert.assertEquals(1,customer.getTicketList().size());
    }

    @Test
    @DisplayName("Reserve seat while ordering")
    public void RemoveSeatFromEventWhenTicketUnderProcess() throws IllegalTicketCreationException {
        //Creates a ticket.
        ticketHandler.createTicket(manySeatsEvent,-1);
        //Check if seats are n-1
        Assertions.assertEquals(99, manySeatsEvent.getEventSeats().size());
        //Adds one more ticket to handler.
        ticketHandler.createTicket(manySeatsEvent,-1);
        //Seats left should now be n-2
        //Check if seats are n-2
        Assertions.assertEquals(98, manySeatsEvent.getEventSeats().size());

    }

    @Test
    @DisplayName("Un-reserve seat when order is cancelled")
    public void EventGetSeatBackIfProcessIsCanceled() throws IllegalTicketCreationException {
        //Current ticket size == n
        ticketHandler.createTicket(manySeatsEvent,-1);
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
    public void EventHasCorrectDate() {
        Assertions.assertEquals(LocalDate.of(2000,1,1), oneSeatEvent.getDate());
    }
    @Test
    public void EventHasCorrectName() {
        Assertions.assertEquals("OneSpotEvent", oneSeatEvent.getName());
    }
    @Test
    public void EventHasCorrectVenue() throws VenueHasNoSeatsException {
        Venue venue = new Venue(256, "Dorororo");
        oneSeatEvent = new Event("JustOneSpotLeft", venue, LocalDate.of(2000,1,1), 100, organizer);
        Assertions.assertEquals(venue, oneSeatEvent.getVenue());
    }

    @DisplayName("Sjekker antall billetter går ned i Event når billett er reservert")
    @Test
    public void confirmThatTicketIsReservedOnReservation() throws IllegalTicketCreationException {
        int numberOfSeats = manySeatsEvent.getEventSeats().size();
        ticketHandler.createTicket(manySeatsEvent, 4);
        Assertions.assertEquals(manySeatsEvent.getEventSeats().size(), numberOfSeats-1);
    }

    @DisplayName("Sjekker at reservert sete i TicketHandler og Event er samme objekt")
    @Test
    public void checkThatTheCorrectSeatIsReservedOnReservation() throws IllegalTicketCreationException {
        Venue.Seat x = manySeatsEvent.getEventSeats().get(manySeatsEvent.getEventSeats().size()-1);
        ticketHandler.createTicket(manySeatsEvent, manySeatsEvent.getEventSeats().size()-1);
        Assertions.assertEquals(x, ticketHandler.getTickets().get(0).getSeat());
    }


    @DisplayName("Throws exception if event is created as a seated event when venue has no seats")
    @Test
    public void throwsExceptionWhenTryingToCreateTicketWhenNoMoreTicketsAvailable() {
        Organizer organizer = new Organizer("Dandelion", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        Venue noSeatVenue = new Venue(0, "NoSeatesInThisPlace");
        Assertions.assertThrows(VenueHasNoSeatsException.class, () -> new Event("SeatedEventWithoutSeatsWHAT??!!", noSeatVenue, LocalDate.now(), 175, organizer));
    }
}