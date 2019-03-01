package TicketService.Model;

public class Venue {
    private String venueId;
    private String venueName;

    public Venue(String venueId, String venueName) {
        this.venueId = venueId;
        this.venueName = venueName;
    }

    public String getVenueId() {
        return venueId;
    }

    public String getVenueName() {
        return venueName;
    }
}
