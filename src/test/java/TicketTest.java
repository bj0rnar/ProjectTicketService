import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

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

    @DisplayName("Sjekker at ID på billett økes ved hver ny billett")
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
        ticketHandler.giveTicketToCustomer();
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
        //customer = new Customer("A","B","A@B.COM"); <--- resets the list.
        Assert.assertEquals(0, customer.getTicketList().size());
        ticketHandler.giveTicketToCustomer();
        Assert.assertEquals(1, customer.getTicketList().size());
        Assert.assertEquals(manySeatsEvent.getName(), customer.getTicketList().get(0).getEvent().getName());
    }

    @Test
    public void TicketHasCorrectPrice() {
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertEquals(250, ticketHandler.getTickets().get(0).getPrice());
    }

    @DisplayName("Sjekke at pris blir summert")
    @Test
    public void TicketHandlerReturnCorrectTotalPrice() {
        ticketHandler.createTicket(manySeatsEvent,0);
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertEquals(500, ticketHandler.calculatedTotalPrice());
    }

    @DisplayName("Sjekker om sete faktisk blir reservert når noen reserverer det")
    @Test
    public void checkSeatAvailability(){
        ticketHandler.createTicket(manySeatsEvent, 4);
        ticketHandler.giveTicketToCustomer();
        Customer customer2 = new Customer("x", "y", "z");
        TicketHandler ticketHandler2 = new TicketHandler(customer2);
        ticketHandler2.createTicket(manySeatsEvent, 4);
        ticketHandler2.giveTicketToCustomer();
    }

    @DisplayName("Sete 0-100 blir laget. Hvis man legger på 10 seter til, vil sete nr 101 få seteNr 1. (to be fixed)")
    @Test
    public void newlyCreatedSeatsAreFucked(){
        //TODO: Add functionality for adding seats to already created Venue
        //Currently adds wrong seatNumber to added seats. Seat 100 gets seatNumber 1 in below example
        System.out.println(manySeatsEvent.getEventSeats().get(0).getSeatNumber());
        System.out.println(manySeatsEvent.getEventSeats().get(99).getSeatNumber());
        manySeatsEvent.getVenue().addSeats(10);
        System.out.println(manySeatsEvent.getEventSeats().get(101).getSeatNumber());
    }

    @DisplayName("Sjekk at 1 kvittering blir lagt til i kundens kvitteringsliste når kunden kjøper billett")
    @Test
    public void receiptAreAddedToCustomerReceiptList(){
        ticketHandler.createTicket(manySeatsEvent, 4);
        ticketHandler.giveTicketToCustomer();
        Assertions.assertEquals(1, customer.getReceiptList().size());
    }

    @DisplayName("Sjekk at alle kvitteringer blir lagt til i kundens kvitteringsliste når kunden kjøper nye billetter")
    @Test
    public void multipleReceiptsAddedToCustomerReceiptList(){
        ticketHandler.createTicket(manySeatsEvent, 9);
        ticketHandler.createTicket(manySeatsEvent,7);
        ticketHandler.createTicket(oneSeatEvent, 0);
        ticketHandler.giveTicketToCustomer();
        Assertions.assertEquals(3, customer.getReceiptList().size());
    }

    @DisplayName("Printer ut alle billetter for demo")
    @Test
    public void printAllTicketsForUser(){
        ticketHandler.createTicket(manySeatsEvent, 9);
        ticketHandler.createTicket(manySeatsEvent,7);
        ticketHandler.createTicket(oneSeatEvent, 0);
        ticketHandler.giveTicketToCustomer();
        ticketHandler.printAllTickets();
    }

    @DisplayName("Sjekker tickethandler logikk. tickethandler.getTickets er et midlertidig array som funker som handlekurv. customer.getTicketList er permanent")
    @Test
    public void ticketHandlerArrayNowWorksAsTemporaryList(){
        ticketHandler.createTicket(manySeatsEvent, 1);
        ticketHandler.createTicket(manySeatsEvent, 4);
        Assertions.assertEquals(2, ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer();
        Assertions.assertEquals(0, ticketHandler.getTickets().size());
        Assertions.assertEquals(2, customer.getTicketList().size());
        ticketHandler.createTicket(manySeatsEvent, 2);
        Assertions.assertEquals(1, ticketHandler.getTickets().size());
        ticketHandler.giveTicketToCustomer();
        Assertions.assertEquals(3, customer.getTicketList().size());
        Assertions.assertEquals(0, ticketHandler.getTickets().size());

    }

    @DisplayName("Sammenlign kvittering med StringBuilder for å sjekke at alt er på plass")
    @Test
    public void verifiyCorrectlyWrittenReceiptFromEvent(){
        StringBuilder s = new StringBuilder();
        s.append("Customer: " + "A " + "B" + "\n");
        s.append("manySeatedEvent " +  LocalDate.of(2000,1,1) + "\n");
        s.append("Hall 42" + " Seat: " + 1 + "\n");
        s.append("Price: " + 250);


        ticketHandler.createTicket(manySeatsEvent, 1);
        ticketHandler.giveTicketToCustomer();

        int index = customer.getReceiptList().size();


        Assertions.assertEquals(s.toString(), customer.getReceiptList().get(index-1));

    }


}
