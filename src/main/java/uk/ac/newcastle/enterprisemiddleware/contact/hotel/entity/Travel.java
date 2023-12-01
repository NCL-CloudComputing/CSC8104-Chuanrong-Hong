package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@Entity
@XmlRootElement
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "customer_id")
    @NotNull
    private Long customerId;

    @Column(name = "hotel_id")
    @NotNull
    private Long hotelId;

    @Column(name = "plane_id")
    @NotNull
    private Long plane_id;

    @Column(name = "taxi_id")
    @NotNull
    private Long taxi_id;

    @Column(name = "bookingTime_hotel")
    @NotNull
    private LocalDate bookingTime_hotel;

    @Column(name = "bookingTime_plane")
    @NotNull
    private LocalDate bookingTime_plane;

    @Column(name = "bookingTime_taxi")
    @NotNull
    private LocalDate bookingTime_taxi;

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

    public Long getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(Long plane_id) {
        this.plane_id = plane_id;
    }

    public Long getTaxi_id() {
        return taxi_id;
    }

    public void setTaxi_id(Long taxi_id) {
        this.taxi_id = taxi_id;
    }

    public LocalDate getBookingTime_hotel() {
        return bookingTime_hotel;
    }

    public void setBookingTime_hotel(LocalDate bookingTime_hotel) {
        this.bookingTime_hotel = bookingTime_hotel;
    }

    public LocalDate getBookingTime_plane() {
        return bookingTime_plane;
    }

    public void setBookingTime_plane(LocalDate bookingTime_plane) {
        this.bookingTime_plane = bookingTime_plane;
    }

    public LocalDate getBookingTime_taxi() {
        return bookingTime_taxi;
    }

    public void setBookingTime_taxi(LocalDate bookingTime_taxi) {
        this.bookingTime_taxi = bookingTime_taxi;
    }

}
