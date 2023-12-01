package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.ServiceReturn;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Travel;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity.FlightBooking;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity.TaxiBooking;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    @Inject
    TravelRepository travelRepository;

    @Path("/orderTravel")
    @GET
    @Operation(description = "order travel")
    public Response orderTravel(Travel travel) throws JsonProcessingException {

        Long plane_order_id;
        Long taxi_order_id;
        Long hotel_order_id;

        //order plane
        FlightBooking flightBooking = new FlightBooking(travel.getCustomerId(),travel.getPlane_id(), travel.getBookingTime_plane());
        Response  plane_res =  flightAgent.order(flightBooking);

        if(plane_res.getStatus() == 400){
            return Response.status(Response.Status.CONFLICT).build();
        }else {
            Map map = convertJson(plane_res.getEntity());
            plane_order_id = Long.parseLong(map.get("id").toString());
        }

        //order taxi
        TaxiBooking taxiBooking = new TaxiBooking(travel.getCustomerId(),travel.getTaxi_id(), travel.getBookingTime_taxi());
        Response  taxi_res =  taxiAgent.order(taxiBooking);

        if(taxi_res.getStatus() == 400){
            flightAgent.del(plane_order_id);
            return Response.status(Response.Status.CONFLICT).build();
        }else {
            Map map = convertJson(taxi_res.getEntity());
            taxi_order_id = Long.parseLong(map.get("id").toString());
        }

        //order hotel
        Booking booking_hotel = new Booking(travel.getCustomerId(),travel.getTaxi_id(), travel.getBookingTime_hotel());
        Response  hotel_res =  hotelAgent.order(booking_hotel);
        if(hotel_res.getStatus() == 400){
            flightAgent.del(plane_order_id);
            taxiAgent.del(taxi_order_id);
            return Response.status(Response.Status.CONFLICT).build();
        }else{
            Map map = convertJson(hotel_res.getEntity());
            hotel_order_id = Long.parseLong(map.get("id").toString());
        }

        //order success , save the data into database
        travel.setPlane_id(plane_order_id);
        travel.setTaxi_id(taxi_order_id);
        travel.setHotelId(hotel_order_id);
        travelRepository.create(travel);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("/getAllTravel")
    @GET
    @Operation(description = "get All Travel")
    public Response getAllTravel(){
        //order flight
        List<Travel> resList = travelRepository.getAllBooking();

        return Response.status(Response.Status.OK).entity(new ServiceReturn(resList,true)).build();
    }

    @Path("/delTravel/{id}")
    @DELETE
    @Operation(description = "delete Travel")
    public Response delTravel(@PathParam("id") long id){
        //order flight
        Boolean res = travelRepository.delBooking(id);

        return Response.status(Response.Status.OK).entity(new ServiceReturn(res? "delete success":"delete failed",res ? true:false)).build();
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

    Map<String,String> convertJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(o);
        Map<String,String> m = objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
        return m;
    }
}
