package Fakes;

import TicketService.Gateway.AdminEventGateway;
import TicketService.Gateway.TicketGateway;
import TicketService.Gateway.VenueAdministrationGateway;
import TicketService.Model.User.Organiser;

public class FakeOrganiser extends Organiser {
    VenueAdministrationGateway venueAdministrationGateway;
    AdminEventGateway adminEventGateway;
    TicketGateway ticketGateway;
}
