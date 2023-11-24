package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
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

    public Booking(@NotNull Long customerId, @NotNull Long hotelId, @NotNull LocalDate bookingTime) {
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.bookingTime = bookingTime;
    }

    public Booking(){

    }

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
