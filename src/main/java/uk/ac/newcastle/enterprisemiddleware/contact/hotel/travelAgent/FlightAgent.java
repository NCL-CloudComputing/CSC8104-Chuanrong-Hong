package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity.FlightBooking;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "flight-api")
public interface FlightAgent {

    @POST
    @Path("/bookings")
    @Produces("application/json")
    Response order(FlightBooking flightBooking);

    @DELETE
    @Path("/delBookings")
    @Produces("application/json")
    Response del(long id);
}
