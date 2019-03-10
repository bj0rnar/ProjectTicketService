package TicketService.Model;

import java.util.ArrayList;

public class Venue {
    ArrayList<Seat> Seats = new ArrayList<>();
    String Name;

    public Venue(int totalSeats, String name) {
        for(int x=1; x<=totalSeats;x++) {
            Seats.add(new Seat(x));
        }
        this.Name = name;
    }
    public Venue(String name) {
        this.Name = name;
    }

    public ArrayList<Seat> getSeats() {
        return Seats;
    }

    public String getName() {
        return Name;
    }

    public class Seat {
        int seatNumber;
        public Seat(int seatNumber) {
            this.seatNumber = seatNumber;
        }

        public int getSeatNumber() {
            return seatNumber;
        }
    }
}
