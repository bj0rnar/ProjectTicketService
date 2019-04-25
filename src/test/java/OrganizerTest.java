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
    private int currentStateOfDB, currentStateOfLocal;

    @BeforeEach
    public void setup(){
        organizer = new Organizer("BillyBob", "MyPassword","James", "Bond", "007@MI6.UK");
        eventHandler = new EventHandler(organizer);
        v = new Venue(48, "Sjoa samvirkelag");
        currentStateOfDB = FakeDB.uploadedEvents.size();
        currentStateOfLocal = organizer.getEvents().size();
    }

    @DisplayName("Organizer har en liste over sine egne events, Databasen har liste over alle. Sjekker at nye events blir lastet opp til begge")
    @Test
    public void verifyThatTheSameEventIsUploadedToBothLocalAndDB(){
        eventHandler.createNewNoneSeatedEvent("rere", v , LocalDate.now(), 123, 10);
        Assertions.assertEquals(organizer.getEvents().get(currentStateOfLocal), FakeDB.uploadedEvents.get(currentStateOfDB));
    }

    @DisplayName("Standard opprettning av arrangement")
    @Test
    public void organizerCanCreateEvents(){
        int indexOfOrganizerList = organizer.getEvents().size();
        eventHandler.createNewNoneSeatedEvent("rere", v , LocalDate.now(), 123, 10);
        Assertions.assertNotNull(organizer.getEvents().get(indexOfOrganizerList));
    }

    @DisplayName("Se beskrivelse over. Sjekker her at man fjerner fra begge events")
    @Test
    public void removeEventFromBothLocalAndDB(){
        eventHandler.createNewNoneSeatedEvent("Tjohei", v , LocalDate.now(), 123, 10);
        currentStateOfDB++;
        currentStateOfLocal++;
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        eventHandler.deleteEvent(organizer.getEvents().get(0));
        currentStateOfDB--;
        currentStateOfLocal--;
        Assertions.assertEquals(currentStateOfLocal, organizer.getEvents().size());
        Assertions.assertEquals(currentStateOfDB, FakeDB.uploadedEvents.size());
    }

    @Test
    public void addVenueFromBothLocalAndDB(){
        int currentStateOfVenueDB = FakeDB.officialVenueList.size();
        int currentStateOfLocalVenueDB = organizer.getUserCreatedVenues().size();
        eventHandler.createNewVenue(1, "hei");
        ++currentStateOfVenueDB;
        ++currentStateOfLocalVenueDB;
        //Checks if last element is the same.
        Assertions.assertEquals(FakeDB.officialVenueList.get(currentStateOfVenueDB-1), organizer.getUserCreatedVenues().get(currentStateOfLocalVenueDB-1));
    }

    @Test
    public void customVenueExistsInBothLocalAndDB() {
        int currentStateOfVenueDB = FakeDB.officialVenueList.size();
        int currentStateOfLocalVenueDB = organizer.getUserCreatedVenues().size();
        eventHandler.createNewVenue(23, "venueOne");
        Assertions.assertEquals((currentStateOfVenueDB+1), FakeDB.officialVenueList.size());
        Assertions.assertEquals((currentStateOfLocalVenueDB+1), organizer.getUserCreatedVenues().size());
        Assertions.assertEquals("venueOne", organizer.getUserCreatedVenues().get(currentStateOfLocalVenueDB).getName());
        Assertions.assertEquals("venueOne", FakeDB.officialVenueList.get(currentStateOfVenueDB).getName());
    }

    @Test
    public void removeVenueFromBothLocalAndDB(){
        eventHandler.createNewVenue(23, "venueTwo");
        int currentStateOfVenueDB = FakeDB.officialVenueList.size();
        int currentStateOfLocalVenueDB = organizer.getUserCreatedVenues().size();
        eventHandler.deleteVenue(FakeDB.officialVenueList.get(currentStateOfVenueDB-1));
        Assertions.assertEquals((currentStateOfVenueDB-1), FakeDB.officialVenueList.size());
        Assertions.assertEquals((currentStateOfLocalVenueDB-1), organizer.getUserCreatedVenues().size());
    }



}
