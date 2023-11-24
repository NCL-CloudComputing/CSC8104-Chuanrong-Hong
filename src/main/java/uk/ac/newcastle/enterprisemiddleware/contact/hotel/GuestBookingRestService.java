package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Customer;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Hotel;
import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.ServiceReturn;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;



    @Path("/hotel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class GuestBookingRestService {

        @Inject
        CustomerService customerService;

        @Inject
        UserTransaction transaction;

        @Inject
        HotelService hotelService;

        @Inject
        BookingService bookingService;

        @Inject
        @Named("logger")
        Logger log;

        @Path("/customerCreate")
        @POST
        @Operation(description = "Add a new Customer to the database")
        @APIResponses(value = {
                @APIResponse(responseCode = "201", description = "Customer created successfully."),
                @APIResponse(responseCode = "400", description = "Invalid Customer supplied in request body"),
                @APIResponse(responseCode = "409", description = "Customer supplied in request body conflicts with an existing Customer"),
                @APIResponse(responseCode = "500", description = "An unexpected error occurred whilst processing the request")
        })
        public Response createCustom(Customer customer){
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED).entity(new ServiceReturn("success!" , true));
            if (customer == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ServiceReturn("request body is null" , false)).build();
            }

            try{
                transaction.begin();
                customerService.createCustom(customer);
                transaction.commit();
            }catch (Exception e){
                builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServiceReturn(e.getMessage() , false));
                log.info("commit error ----->" + customer.toString());
                log.info("cause ----->" + e.getMessage() + ":" + e.getCause());
                if (e.getCause() instanceof ConstraintViolationException || e.getCause() instanceof PersistenceException){
                    Set<ConstraintViolation<?>> violations = new LinkedHashSet<>();
                    StringBuffer sb = new StringBuffer();
                    if(e.getCause() instanceof ConstraintViolationException){
                        violations = ((ConstraintViolationException)e.getCause()).getConstraintViolations();
                    }
                    if(e.getCause() instanceof PersistenceException){
                        sb.append("already have same record");
                    }
                    for (ConstraintViolation<?> violation : violations) {
                        String errorMessage = violation.getMessage();
                        sb.append(violation.getPropertyPath());
                        sb.append(":");
                        sb.append(errorMessage);
                        sb.append("|");
                    }
                    sb.substring(0,sb.length()-1);
                    builder = Response.status(Response.Status.CONFLICT).entity(new ServiceReturn(sb.toString() , false));
                }
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    log.info("rollback error ----->" + ex.getMessage() + ":" + e.getCause());
                }
            }

            return builder.build();
        }

        @Path("/getAllCustomers")
        @GET
        @Operation(description = "get all customers")
        public Response getAllCustom(){
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED);
            return builder.entity(new ServiceReturn(customerService.getAllCustomers(),true)).build();
        }

        @Path("/hotelCreate")
        @POST
        @Operation(description = "Add a new Hotel to the database")
        @APIResponses(value = {
                @APIResponse(responseCode = "201", description = "Hotel created successfully."),
                @APIResponse(responseCode = "400", description = "Invalid Hotel supplied in request body"),
                @APIResponse(responseCode = "409", description = "Hotel supplied in request body conflicts with an existing Hotel"),
                @APIResponse(responseCode = "500", description = "An unexpected error occurred whilst processing the request")
        })
        public Response createCustom(Hotel hotel){
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED).entity(new ServiceReturn("success!" , true));
            if (hotel == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity(new ServiceReturn("request body is null" , false)).build();
            }

            try{
                transaction.begin();
                hotelService.createHotel(hotel);
                transaction.commit();
            }catch (Exception e){
                builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ServiceReturn(e.getMessage() , false));
                log.info("commit error ----->" + hotel.toString());
                log.info("cause ----->" + e.getMessage() + ":" + e.getCause());
                if (e.getCause() instanceof ConstraintViolationException || e.getCause() instanceof PersistenceException){
                    Set<ConstraintViolation<?>> violations = new LinkedHashSet<>();
                    StringBuffer sb = new StringBuffer();
                    if(e.getCause() instanceof ConstraintViolationException){
                        violations = ((ConstraintViolationException)e.getCause()).getConstraintViolations();
                    }
                    if(e.getCause() instanceof PersistenceException){
                        sb.append("already have same record");
                    }
                    for (ConstraintViolation<?> violation : violations) {
                        String errorMessage = violation.getMessage();
                        sb.append(violation.getPropertyPath());
                        sb.append(":");
                        sb.append(errorMessage);
                        sb.append("|");
                    }
                    sb.substring(0,sb.length()-1);
                    builder = Response.status(Response.Status.CONFLICT).entity(new ServiceReturn(sb.toString() , false));
                }
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    log.info("rollback error ----->" + ex.getMessage() + ":" + e.getCause());
                }
            }

            return builder.build();
        }


        @Path("/getAllHotels")
        @GET
        @Operation(description = "get all Hotels")
        public Response getAllHotels(){
            Response.ResponseBuilder builder = Response.status(Response.Status.CREATED);
            return builder.entity(new ServiceReturn(hotelService.getAllHotels(),true)).build();
        }





    }
