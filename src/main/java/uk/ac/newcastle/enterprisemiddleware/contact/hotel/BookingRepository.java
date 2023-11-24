package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Booking;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class BookingRepository {
    @Inject
    @Named("logger")
    Logger log;

    @Inject
    EntityManager em;

    void create(Booking booking) {
        em.persist(booking);
    }

    List<Booking> getAllBookingById(long id){
        Query query = em.createQuery("select c from Booking c where customer_id = "+ id);
        return query.getResultList();
    }

    Boolean delBooking(long id){
        Query query = em.createQuery("delete from Booking where id = "+ id);
        return query.executeUpdate() == 1;
    }
}

