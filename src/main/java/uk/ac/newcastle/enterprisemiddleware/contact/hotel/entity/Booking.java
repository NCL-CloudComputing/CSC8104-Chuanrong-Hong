package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;


 /**
 *  * Entity.It marks this class as an entity class, that is, instances of this class need to be persisted to the database
 *  *In JPA, an entity class usually corresponds to a table in the database.
 *  * XmlRootElement.It indicates that this class can be mapped to XML. This annotation is typically used to support serialization of objects into XML format.
 *  * The @Table annotation specifies which table in the database this entity class maps to.
 *  * UniqueConstraint.A uniqueness constraint is defined to ensure that the combination of the "hotel_id" and "booking_time" columns is unique
 *
 *  */

@Entity
@NamedQueries({
})
@XmlRootElement
@Table(name = "booking", uniqueConstraints = @UniqueConstraint(columnNames = {"hotel_id" , "booking_time"}))
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "customer_id")
    @NotNull
    private Long customerId;

    @Column(name = "hotel_id")
    @NotNull
    private Long hotelId;

    @Column(name = "booking_time")
    @NotNull
    private LocalDate bookingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDate bookingTime) {
        this.bookingTime = bookingTime;
    }
}
