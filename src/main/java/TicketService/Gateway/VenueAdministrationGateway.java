package TicketService.Gateway;

import TicketService.Model.Seat;
import TicketService.Model.Venue;

public class VenueAdministrationGateway {

    public Venue createSeatedVenue(String venueId, String venueName, int capacity) {
        Venue result = new Venue(venueId);

        result.setVenueName(venueName);
        result.setSeats(defineSeating(capacity));

        return result;
    }

    public Venue createUnseatedVenue(String venueId, String venueName) {
        Venue result = new Venue(venueId);
        result.setVenueName(venueName);
        return result;
    }

    private Seat[] defineSeating(int capacity) {
        Seat[] seats = new Seat[capacity];

        if (capacity > 0) {
            seats = new Seat[capacity];
            for(int i= 0; i < seats.length; i++){
                seats[i] = new Seat(i);
            }
        }

        return seats;
    }
}