import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TicketTest {

    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    TicketHandler ticketHandler;


    @BeforeEach
    public void eachStartUp() {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        Organizer organizer = new Organizer("TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("A","B","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100,true, organizer);
        manySeatsEvent = new Event("manySeatedEvent", manySpotVenue, LocalDate.of(2000,1,1),250,true, organizer);

    }

    @Test
    public void EachTicketGetsUniqueIdWhenTicketIsCreated(){
        int idChecker;
        Ticket ticket = new Ticket(manySeatsEvent);
        idChecker = ticket.getId();
        Assert.assertEquals(idChecker, ticket.getId());
        Ticket secondTicket = new Ticket(manySeatsEvent);
        idChecker++;
        Assert.assertEquals(idChecker, secondTicket.getId());
        Ticket thirdTicket = new Ticket(manySeatsEvent);
        idChecker++;
        Assert.assertEquals(idChecker, thirdTicket.getId());
    }

    @Test
    public void TicketHandlerCanReceiveTicket() {
        Assert.assertEquals(0, ticketHandler.getTickets().size());
        ticketHandler.getTickets().add(new Ticket(manySeatsEvent));
        Assert.assertEquals(1, ticketHandler.getTickets().size());
    }

    @Test
    public void TicketHasCorrectEventGivenToIt() {
        Event event = new Event("Gutta på tur", manySeatsEvent.getVenue(), LocalDate.of(2020, 1, 1),299,true,new Organizer("a","b","c"));
        ticketHandler.createTicket(event, 0);
        ticketHandler.giveTicketToCustomer(customer);
        Assertions.assertEquals("Gutta på tur", customer.getTicketList().get(0).getEvent().getName());
    }

    @Test
    public void TicketCanGetSeat() {
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertNotNull(ticketHandler.getTickets().get(0).getSeat());
    }


    @Test
    public void TicketHandlerCanCreateCompleteTicketAndGiveToUser() {
        ticketHandler.createTicket(manySeatsEvent,0);
        customer = new Customer("A","B","A@B.COM");
        Assert.assertEquals(0, customer.getTicketList().size());
        ticketHandler.giveTicketToCustomer(customer);
        Assert.assertEquals(1, customer.getTicketList().size());
        Assert.assertEquals(manySeatsEvent.getName(), customer.getTicketList().get(0).getEvent().getName());
    }

    @Test
    public void TicketHasCorrectPrice() {
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertEquals(250, ticketHandler.getTickets().get(0).getPrice());
    }

    @Test
    public void TicketHandlerReturnCorrectTotalPrice() {
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertEquals(500, ticketHandler.calculatedTotalPrice());
    }

    @Test
    public void checkSeatAvailability(){
        ticketHandler.createTicket(manySeatsEvent, 4);
        ticketHandler.giveTicketToCustomer(customer);
        Customer customer2 = new Customer("x", "y", "z");
        TicketHandler ticketHandler2 = new TicketHandler(customer2);
        ticketHandler2.createTicket(manySeatsEvent, 4);
        ticketHandler2.giveTicketToCustomer(customer2);
    }

    @Test
    public void newlyCreatedSeatsAreFucked(){
        System.out.println(manySeatsEvent.getEventSeats().get(0).getSeatNumber());
        System.out.println(manySeatsEvent.getEventSeats().get(99).getSeatNumber());
        manySeatsEvent.getVenue().addSeats(10);
        System.out.println(manySeatsEvent.getEventSeats().get(101).getSeatNumber());
    }


}
