package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Customer;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class CustomerRepository {
    @Inject
    @Named("logger")
    Logger log;

    @Inject
    EntityManager em;

    void create(Customer customer) {
        em.persist(customer);
    }

    List<Customer> getAllCustomers(){
        Query query = em.createQuery("select c from Customer c");
        return query.getResultList();
    }

    Boolean checkCustomer(long id){
        Query query = em.createQuery("select c from Customer c where id = "+ id);
        return query.getResultList().size() > 0 ? true : false;
    }
}
