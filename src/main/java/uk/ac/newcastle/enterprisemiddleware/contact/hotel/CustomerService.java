package uk.ac.newcastle.enterprisemiddleware.contact.hotel;

import uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity.Customer;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class CustomerService {
    @Inject
    CustomerRepository customerRepository;

    void createCustom(Customer customer){ customerRepository.create(customer); }


    List<Customer> getAllCustomers(){ return customerRepository.getAllCustomers();}

    Boolean checkCustomer(long id){ return customerRepository.checkCustomer(id);}
}
