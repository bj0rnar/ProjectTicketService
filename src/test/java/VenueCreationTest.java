import TicketService.Model.EventHandler;
import TicketService.Users.Organizer;
import TicketService.DataAccess.FakeDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VenueCreationTest {

    private EventHandler eventHandler;
    private Organizer organizer;

    @BeforeEach
    public void setup(){
        organizer = new Organizer("DisOrganizer", "MyPassword","TicketService", "ServiceTicket","Ticket@service.com");
        eventHandler = new EventHandler(organizer);
    }

    @Test
    public void organizerCreatesANewVenueAndItsUsableForEvents(){

        int DBIndexForVenues = FakeDB.officialVenueList.size();
        int DBIndexForEvents = FakeDB.uploadedEvents.size();


        eventHandler.createNewVenue(1, "Heisann","Gata 2, Halden");
        eventHandler.createNewNoneSeatedEvent("Her skjer dæ ting", organizer.getUserCreatedVenues().get(0), LocalDate.now(), 12, 100);
        ++DBIndexForVenues;
        ++DBIndexForEvents;
        Assertions.assertEquals(1, organizer.getUserCreatedVenues().size());
        Assertions.assertEquals(DBIndexForVenues, FakeDB.officialVenueList.size());
        Assertions.assertEquals(DBIndexForEvents, FakeDB.uploadedEvents.size());
        Assertions.assertEquals(1, organizer.getEvents().size());
    }

    @Test
    public void organizerCreatesJustAVenue(){
        eventHandler.createNewVenue(7, "Heime sjå ho mor","Gata 2, Halden");
        Assertions.assertNotNull(organizer.getUserCreatedVenues().get(0));
    }
}
