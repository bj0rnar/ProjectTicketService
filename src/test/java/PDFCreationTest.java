import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Utility.PDFCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static TicketService.Controller.MainWindowController.customer;
import TicketService.Model.Ticket;

public class PDFCreationTest {
    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    Organizer organizer;
    TicketHandler ticketHandler;


    @BeforeEach
    public void fixMyTestClass(){
        Venue oneSpotVenue = new Venue(1, "Hall 2");
        Venue manySpotVenue = new Venue(100, "DU ska sitta her");
        organizer = new Organizer("TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("Adolf","Critler","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100,true, organizer);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),100,true, organizer);
        ticketHandler.createTicket(manySeatsEvent, 14);
        ticketHandler.giveTicketToCustomer();
    }

    @Test
    public void cmonBaby(){
        try {
            PDFCreator.initializePdfCreation(customer.getTicketList().get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
