package TicketService.DataAccess;

import TicketService.Exception.VenueHasNoSeatsException;
import TicketService.Model.Event;
import TicketService.Model.Venue;
import TicketService.Users.Customer;
import TicketService.Users.Organizer;
import TicketService.Users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class FakeDB implements IRepository{

    public static final ArrayList<Event> uploadedEvents = new ArrayList<>();
    public static final ArrayList<Venue> officialVenueList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();


    //Dummy data
    public static void CreateEvents() throws VenueHasNoSeatsException {
        IRepository repo = new FakeDB();
        if(uploadedEvents.size() == 0) {
            uploadedEvents.add(new Event("TG19", repo.getVenues().get(0), LocalDate.of(2019,3,21), 100,  new Organizer("Egil", "MyPassword","Eddy", "Normann","eventMaker@mail.com")));
            uploadedEvents.add(new Event("EnSetersEvent", repo.getVenues().get(1), LocalDate.of(2019,3,21), 299, new Organizer("Haraldio", "MyPassword","Eddy", "Normann","eventMaker@mail.com")));
            uploadedEvents.add(new Event("Sopptur", repo.getVenues().get(2), LocalDate.of(2019,3,21), 100, new Organizer("Kimblalololo", "MyPassword","Eddy", "Normann","eventMaker@mail.com"), 33));
        }
    }
    //Dummy data
    public static void CreateUsers() {
        userList.add(new Customer("Mats", "MyPassword","Kombo", "Trombo","Kombotrombo@mail.com"));
        userList.add(new Organizer("Lars", "MyPassword","Damba", "Samba","DambaSamba@mail.com"));
    }

    public static User authUserLogin(String username, String password) {
        for(User user : userList) {
            if(user.getUsername().equals(username))
                if(user.getPassword().equals(password))
                    return user;
        }
        return null;
    }
    //Dummy data
    public static void CreateVenues() {
        officialVenueList.add(new Venue(100, "Hall 1","Gata 2, Halden"));
        officialVenueList.add(new Venue(1, "Hall 2","Gata 2, Halden"));
        officialVenueList.add(new Venue(0,"Skogen","Gata 2, Halden"));

    }

    //Create dummy data
    public static ArrayList<Event> getEventList() {
        if(uploadedEvents.isEmpty()) {
            try {
                CreateEvents();
            } catch (VenueHasNoSeatsException e) {
                e.printStackTrace();
            }
        }
        return uploadedEvents;
    }

    @Override
    public ArrayList<Venue> getVenues() {
        if(officialVenueList.size() == 0) {
            CreateVenues();
        }
        return officialVenueList;
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public boolean uploadUser(User user) {
        for(User userDb : userList) {
            if(userDb.getUsername().equals(user.getUsername()))
                return false;
        }
        userList.add(user);
        return true;
    }

    @Override
    public ArrayList<User> getUsers() {
        return userList;
    }

    @Override
    public void uploadEvent(Event e) {
        uploadedEvents.add(e);
    }

    @Override
    public void deleteEvent(Event e) {
        uploadedEvents.remove(e);
    }

    @Override
    public ArrayList<Event> getEvents() {
        return new ArrayList<>(getEventList());
    }

    @Override
    public void uploadVenue(Venue v) {
        officialVenueList.add(v);
    }

    @Override
    public void deleteVenue(Venue v) {
        officialVenueList.remove(v);
    }
}
