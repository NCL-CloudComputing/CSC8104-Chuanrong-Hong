package uk.ac.newcastle.enterprisemiddleware.contact.hotel.travelAgent.entity;

import java.time.LocalDate;

public class TaxiBooking {

    private Long customerId;

    private Long taxiId;

    private LocalDate bookingDate;

    public TaxiBooking(Long customerId, Long taxiId, LocalDate bookingDate) {
        this.customerId = customerId;
        this.taxiId = taxiId;
        this.bookingDate = bookingDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTaxiId() {
        return taxiId;
    }

    public void setTaxiId(Long taxiId) {
        this.taxiId = taxiId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
