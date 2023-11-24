package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity;

import java.time.LocalDate;

public class FlightBooking {

    private Long customerId;

    private Long flightId;

    private LocalDate bookingDate;

    public FlightBooking(Long customerId, Long flightId, LocalDate bookingDate) {
        this.customerId = customerId;
        this.flightId = flightId;
        this.bookingDate = bookingDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
