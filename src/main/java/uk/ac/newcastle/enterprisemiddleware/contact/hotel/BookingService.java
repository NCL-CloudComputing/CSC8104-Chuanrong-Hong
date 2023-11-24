package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class BookingService {
    @Inject
    BookingRepository bookingRepository;

    void createBooking(Booking booking){ bookingRepository.create(booking); }

    List<Booking> getAllBookingById(long id){ return bookingRepository.getAllBookingById(id);}

    Boolean delBooking(long id){return bookingRepository.delBooking(id);}
}
