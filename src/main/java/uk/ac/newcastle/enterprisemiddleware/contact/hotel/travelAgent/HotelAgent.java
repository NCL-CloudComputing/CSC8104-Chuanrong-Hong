package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "hotel-api")
public interface HotelAgent {

    @POST
    @Path("/booking")
    @Produces("application/json")
    Response order(Booking booking);


    @DELETE
    @Path("/delBooking/{id}")
    @Produces("application/json")
    Response del(@PathParam("id")long id);
}
