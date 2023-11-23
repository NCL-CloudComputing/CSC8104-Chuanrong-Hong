package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/hotel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GuestBookingRestService {
    @Inject
    CustomerService customerService;

    @Inject
    HotelService hotelService;

    @Inject
    BookingService bookingService;

    @Inject
    @Named("logger")
    Logger log;




}
