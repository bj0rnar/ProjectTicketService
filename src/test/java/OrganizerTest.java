import TicketService.Model.EventHandler;
import TicketService.Model.Venue;
import TicketService.Users.Organizer;
import TicketService.DataAccess.FakeDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OrganizerTest {

    private Organizer organizer;
    private EventHandler eventHandler;
    private Venue v;
    private int currentStateOfDB, currentStateOfLocal,currentStateOfVenueDB,currentStateOfLocalVenueDB;

    @BeforeEach
    public void setup(){
        organizer = new Organizer("BillyBob", "MyPassword","James", "Bond", "007@MI6.UK");
        eventHandler = new EventHandler(organizer);
        v = new Venue(48, "Sjoa samvirkelag");
        currentStateOfDB = FakeDB.uploadedEvents.size();
        currentStateOfLocal = organizer.getEvents().size();
        currentStateOfVenueDB = FakeDB.officialVenueList.size();
        currentStateOfLocalVenueDB = organizer.getUserCreatedVenues().size();
    }

    @DisplayName("Organizer har en liste over sine egne events, Databasen har liste over alle. Sjekker at nye events blir lastet opp til begge")
    @Test
    public void verifyThatTheSameEventIsUploadedToBothLocalAndDB(){
        eventHandler.createNewEvent("rere", v , LocalDate.now(), 123, false);
        Assertions.assertEquals(organizer.getEvents().get(currentStateOfLocal), FakeDB.uploadedEvents.get(currentStateOfDB));
    }

    @DisplayName("Standard opprettning av arrangement")
    @Test
    public void organizerCanCreateEvents(){
        int indexOfOrganizerList = organizer.getEvents().size();
        eventHandler.createNewEvent("rere", v , LocalDate.now(), 123, false);
        Assertions.assertNotNull(organizer.getEvents().get(indexOfOrganizerList));
    }

    @DisplayName("Se beskrivelse over. Sjekker her at man fjerner fra begge events")
    @Test
    public void removeEventFromBothLocalAndDB(){
        eventHandler.createNewEvent("Tjohei", v, LocalDate.now(), 123, true);
        currentStateOfDB++;
        currentStateOfLocal++;
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        eventHandler.deleteEventFromDB("Tjohei");
        currentStateOfDB--;
        currentStateOfLocal--;
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
    }

    @Test
    public void addVenueFromBothLocalAndDB(){
        eventHandler.createNewVenue(1, "hei");
        ++currentStateOfVenueDB;
        ++currentStateOfLocalVenueDB;
        //Checks if last element is the same.
        Assertions.assertEquals(FakeDB.officialVenueList.get(currentStateOfVenueDB-1), organizer.getUserCreatedVenues().get(currentStateOfLocalVenueDB-1));
    }

    @Test
    public void removeVenueFromBothLocalAndDB(){
        eventHandler.createNewVenue(23, "lal");
        ++currentStateOfLocalVenueDB;
        ++currentStateOfVenueDB;
        Assertions.assertEquals("lal", organizer.getUserCreatedVenues().get(currentStateOfLocalVenueDB-1).getName());
        Assertions.assertEquals("lal", FakeDB.officialVenueList.get(currentStateOfVenueDB-1).getName());
        Assertions.assertEquals(currentStateOfVenueDB, FakeDB.officialVenueList.size());
        Assertions.assertEquals(currentStateOfLocalVenueDB, organizer.getUserCreatedVenues().size());
        eventHandler.deleteVenueFromDB("lal");
        --currentStateOfVenueDB;
        --currentStateOfLocalVenueDB;
        Assertions.assertEquals(currentStateOfVenueDB, FakeDB.officialVenueList.size());
        Assertions.assertEquals(currentStateOfLocalVenueDB, organizer.getUserCreatedVenues().size());
    }



}
