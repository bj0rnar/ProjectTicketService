import TicketService.Exception.IllegalTicketCreationException;
import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.TicketHandler;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Utility.PDFCreator;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.io.TempDir;


public class PDFCreationTest {
    Event oneSeatEvent, manySeatsEvent;
    Customer customer;
    Organizer organizer;
    TicketHandler ticketHandler;

    @Rule
    public TemporaryFolder localFolder = new TemporaryFolder();

    @BeforeEach
    public void setup() throws VenueHasNoSeatsException, IllegalTicketCreationException {
        Venue oneSpotVenue = new Venue(1, "Hall 2","Gata 2, Halden");
        Venue manySpotVenue = new Venue(100, "DU ska sitta her","Gata 2, Halden");
        organizer = new Organizer("RunAutaNames", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        customer = new Customer("Dingeling", "MyPassword","Adolf","Critler","A@B.COM");
        ticketHandler = new TicketHandler(customer);
        oneSeatEvent = new Event("JustOneSpotLeft", oneSpotVenue, LocalDate.of(2000,1,1),100, organizer);
        manySeatsEvent = new Event("JustOneSpotLeft", manySpotVenue, LocalDate.of(2000,1,1),100, organizer);
        ticketHandler.createTicket(manySeatsEvent, 14);
        ticketHandler.payForTicketsWithCreditCard(1233123312331233L, 123);
        localFolder = new TemporaryFolder();
    }

    @Test
    public void writesPDFToFile(@TempDir Path tempDir) throws IOException{
        Path output = tempDir.resolve("test.pdf").toAbsolutePath();
        PDFCreator.createPDFToPath(output.toString(), customer.getTicketList().get(0));
        Assertions.assertTrue(Files.exists(output));
    }
}
