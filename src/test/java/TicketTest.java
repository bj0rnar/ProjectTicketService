import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.*;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.DataAccess.IPaymentOptions;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

public class TicketTest {

    Event oneSeatEvent, manySeatsEvent, noSeatEvent;
    Customer customer;
    TicketHandler ticketHandler;
    IPaymentOptions iPaymentOptions;


    @BeforeEach
    public void eachStartUp() throws VenueHasNoSeatsException {
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "Hall 42");
        Organizer organizer = new Organizer("Dandelion", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("Herald", "MyPassword","A","B","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        noSeatEvent = new Event("noSeatEvent", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer, 77);
        manySeatsEvent = new Event("manySeatedEvent", manySpotVenue, LocalDate.of(2000,1,1),250, organizer);

    }

    @DisplayName("Sjekker at ID på billett økes ved hver ny billett")
    @Test
    public void EachTicketGetsUniqueIdWhenTicketIsCreated() throws IllegalTicketCreationException {
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
    public void TicketHandlerCanReceiveTicket() throws IllegalTicketCreationException {
        Assert.assertEquals(0, ticketHandler.getTickets().size());
        ticketHandler.getTickets().add(new Ticket(manySeatsEvent));
        Assert.assertEquals(1, ticketHandler.getTickets().size());
    }

    @Test
    public void TicketHasCorrectEventGivenToIt() throws VenueHasNoSeatsException, IllegalTicketCreationException {
        Event event = new Event("Gutta på tur", manySeatsEvent.getVenue(), LocalDate.of(2020, 1, 1),299, new Organizer("yeeee","password","a","b","c"));
        ticketHandler.createTicket(event, 0);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals("Gutta på tur", customer.getTicketList().get(0).getEvent().getName());
    }

    @Test
    public void TicketCanGetSeat() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertNotNull(ticketHandler.getTickets().get(0).getSeat());
    }

    @Test
    public void TicketHandlerCanCreateCompleteTicketAndGiveToUser() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,0);
        //customer = new Customer("A","B","A@B.COM"); <--- resets the list.
        Assert.assertEquals(0, customer.getTicketList().size());
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assert.assertEquals(1, customer.getTicketList().size());
        Assert.assertEquals(manySeatsEvent.getName(), customer.getTicketList().get(0).getEvent().getName());
    }

    @Test
    public void TicketHasCorrectPrice() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,0);
        Assertions.assertEquals(250, ticketHandler.getTickets().get(0).getPrice());
    }

    @DisplayName("Sjekke at pris blir summert")
    @Test
    public void TicketHandlerReturnCorrectTotalPrice() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent,-1);
        ticketHandler.createTicket(manySeatsEvent,-1);
        Assertions.assertEquals(500, ticketHandler.calculatedTotalPrice());
    }

    @DisplayName("Sjekker om sete faktisk blir reservert når noen reserverer det")
    @Test
    public void checkSeatAvailability() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 4);
        Customer customer2 = new Customer("Hobba", "MyPassword","x", "y", "z");
        TicketHandler ticketHandler2 = new TicketHandler(customer2);
        Assertions.assertThrows(IllegalTicketCreationException.class, () -> ticketHandler2.createTicket(manySeatsEvent, 4));
    }


    @DisplayName("Sjekk at 1 kvittering blir lagt til i kundens kvitteringsliste når kunden kjøper billett")
    @Test
    public void receiptAreAddedToCustomerReceiptList() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 4);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals(1, customer.getReceiptList().size());
    }

    @DisplayName("Sjekk at alle kvitteringer blir lagt til i kundens kvitteringsliste når kunden kjøper nye billetter")
    @Test
    public void multipleReceiptsAddedToCustomerReceiptList() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 9);
        ticketHandler.createTicket(manySeatsEvent,7);
        ticketHandler.createTicket(oneSeatEvent, -1);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals(3, customer.getReceiptList().size());
    }


    @DisplayName("Sjekker tickethandler logikk. tickethandler.getTickets er et midlertidig array som funker som handlekurv. customer.getTicketList er permanent")
    @Test
    public void ticketHandlerArrayNowWorksAsTemporaryList() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 1);
        ticketHandler.createTicket(manySeatsEvent, 4);
        Assertions.assertEquals(2, ticketHandler.getTickets().size());
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals(0, ticketHandler.getTickets().size());
        Assertions.assertEquals(2, customer.getTicketList().size());
        ticketHandler.createTicket(manySeatsEvent, 2);
        Assertions.assertEquals(1, ticketHandler.getTickets().size());
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        Assertions.assertEquals(3, customer.getTicketList().size());
        Assertions.assertEquals(0, ticketHandler.getTickets().size());

    }

    @DisplayName("Event med seter: Sammenlign kvittering med StringBuilder for å sjekke at alt er på plass")
    @Test
    public void verifiyCorrectlyWrittenReceiptFromEventWithSeats() throws IllegalTicketCreationException {
        StringBuilder s = new StringBuilder();
        s.append("Customer: " + "A " + "B" + "\n");
        s.append("manySeatedEvent " +  LocalDate.of(2000,1,1) + "\n");
        s.append("Hall 42" + " Seat: " + 1 + "\n");
        s.append("Price: " + 250);


        ticketHandler.createTicket(manySeatsEvent, 1);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);

        int index = customer.getReceiptList().size();


        Assertions.assertEquals(s.toString(), customer.getReceiptList().get(index-1));

    }

    @DisplayName("Event uten seter: Sammenlign kvittering med StringBuilder for å sjekke at alt er på plass")
    @Test
    public void verifiyCorrectlyWrittenReceiptFromEventWithoutSeats() throws IllegalTicketCreationException {
        StringBuilder s = new StringBuilder();
        s.append("Customer: " + "A " + "B" + "\n");
        s.append("noSeatEvent " +  LocalDate.of(2000,1,1) + "\n");
        s.append("Hall 2" + "\n");
        s.append("Price: " + 100);


        ticketHandler.createTicket(noSeatEvent);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);

        int index = customer.getReceiptList().size();


        Assertions.assertEquals(s.toString(), customer.getReceiptList().get(index-1));

    }

    @DisplayName("TicketHandler sin liste er handlekurv, customer sin liste er kjøpshistorikk.")
    @Test
    public void customerIsGivenTickets() throws IllegalTicketCreationException {
        int ticketHandlerTemporaryList = ticketHandler.getTickets().size();
        int customerPermanentList = customer.getTicketList().size();

        ticketHandler.createTicket(manySeatsEvent, 17);
        ++ticketHandlerTemporaryList;
        Assertions.assertEquals(ticketHandlerTemporaryList, ticketHandler.getTickets().size());
        Assertions.assertEquals(customerPermanentList, customer.getTicketList().size());

        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);

        --ticketHandlerTemporaryList;
        ++customerPermanentList;

        Assertions.assertEquals(ticketHandlerTemporaryList, ticketHandler.getTickets().size());
        Assertions.assertEquals(customerPermanentList, customer.getTicketList().size());

    }

    @Test
    public void payAtTheBank() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 1);
        int index = customer.getTicketList().size();
        ticketHandler.payForTicketsWithCreditCard(53423233, 123);
        index++;
        Assertions.assertEquals(index, customer.getTicketList().size());
    }

    @Test
    public void payAtThePayPal() throws IllegalTicketCreationException {
        ticketHandler.createTicket(manySeatsEvent, 15);
        int index = customer.getTicketList().size();
        ticketHandler.payForTicketsWithPayPal(1312312, 123);
        index++;
        Assertions.assertEquals(index, customer.getTicketList().size());
    }

    @Test
    public void availableTicketsReducedByOneForEachTicketCreated() throws IllegalTicketCreationException {
        int startNumber = manySeatsEvent.getAvailableTickets();
        ticketHandler.createTicket(manySeatsEvent, -1);
        Assertions.assertEquals(startNumber-1, manySeatsEvent.getAvailableTickets());
    }

    @DisplayName("Throws exception if tickets are made without available tickets spots.")
    @Test
    public void throwsExceptionWhenTryingToCreateTicketWhenNoMoreTicketsAvailable() throws VenueHasNoSeatsException {
        Organizer organizer = new Organizer("Dandelion", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        noSeatEvent = new Event("noSeatEvent", new Venue(1,"OnSeatedEvent"), LocalDate.of(2000,1,1),100, organizer);
        try {
            ticketHandler.createTicket(oneSeatEvent, -1);
        } catch (IllegalTicketCreationException e) {
            e.printStackTrace();
        }
        Assertions.assertThrows(IllegalTicketCreationException.class, () -> new Ticket(oneSeatEvent));
    }

}
