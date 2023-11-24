package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity.FlightBooking;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity.TaxiBooking;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.logging.Logger;

@Path("/travel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TravelAgentService {

    @Inject
    Logger logger;

    @RestClient
    FlightAgent flightAgent;

    @RestClient
    TaxiAgent taxiAgent;

    @RestClient
    HotelAgent hotelAgent;

    @Path("/orderTravel")
    @GET
    @Operation(description = "order travel")
    public Response orderTravel(Booking booking){
        //order flight

            FlightBooking flightBooking = new FlightBooking(1L,1L, LocalDate.now());
            if(flightAgent.order(flightBooking).getStatus() == 400){
                return Response.status(Response.Status.CONFLICT).build();
            }

            TaxiBooking taxiBooking = new TaxiBooking(1L,1L, LocalDate.now());
            if(taxiAgent.order(taxiBooking).getStatus() == 400){
                flightAgent.del(1L);
                return Response.status(Response.Status.CONFLICT).build();
            }


            Booking booking_hotel = new Booking(1L,1L,LocalDate.now());
            if(hotelAgent.order(booking_hotel).getStatus() == 400){
                flightAgent.del(1L);
                taxiAgent.del(1L);
                return Response.status(Response.Status.CONFLICT).build();
            }


        return Response.status(Response.Status.CREATED).build();
    }


    @Path("/test")
    @GET
    @Operation(description = "order travel")
    public Response test(Booking booking){
        //order flight
        Booking booking_hotel = new Booking(1L,1L,LocalDate.now());
        hotelAgent.order(booking_hotel);

        return Response.status(Response.Status.CREATED).build();
    }
}
