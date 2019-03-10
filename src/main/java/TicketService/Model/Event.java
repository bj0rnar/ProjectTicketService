package TicketService.Model;

public class Event {
    private String name;
    private Venue venue;
    private Boolean areSeatsAvailable;

    public Event(String name, Venue venue, Boolean areSeatsAvailable) {
        this.name = name;
        this.venue = venue;
        this.areSeatsAvailable = areSeatsAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue getVenue() {
        return venue;
    }

    public Boolean getAreSeatsAvailable() {
        return areSeatsAvailable;
    }
}
