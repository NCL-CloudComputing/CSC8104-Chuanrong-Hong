package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Travel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class TravelRepository {
    @Inject
    @Named("logger")
    Logger log;

    @Inject
    EntityManager em;

    void create(Travel travel) {
        em.persist(travel);
    }

    List<Travel> getAllBooking(){
        Query query = em.createQuery("select c from travel c ");
        return query.getResultList();
    }

    Boolean delBooking(long id){
        Query query = em.createQuery("delete from travel where id = "+ id);
        return query.executeUpdate() == 1;
    }
}
