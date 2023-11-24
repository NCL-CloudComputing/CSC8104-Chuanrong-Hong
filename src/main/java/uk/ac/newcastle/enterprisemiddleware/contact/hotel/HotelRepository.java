package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Hotel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class HotelRepository {
    @Inject
    @Named("logger")
    Logger log;

    @Inject
    EntityManager em;

    void create(Hotel hotel) {
        em.persist(hotel);
    }

    List<Hotel> getHotel(){
        Query query = em.createQuery("select c from Hotel c");
        return query.getResultList();
    }

    Boolean checkHotel(long id){
        Query query = em.createQuery("select c from Hotel c where id = "+ id);
        return query.getResultList().size() > 0 ? true : false;
    }

}
