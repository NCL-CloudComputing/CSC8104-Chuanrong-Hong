package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
})
@XmlRootElement
@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id" , "booking_time"}))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
}
