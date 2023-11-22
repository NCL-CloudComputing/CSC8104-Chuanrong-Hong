package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
@Table(name = "hotel",uniqueConstraints = @UniqueConstraint(columnNames = "phone_number"))
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Size(min=1,max = 50)
    @Pattern(regexp ="^[A-Za-z]+$",message = "Hotel name must only contain letters")
    @Column(name = "name")
    private String hotelName;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9]{6}$", message = "Please input a correct zip code")
    private String zipCode;

    @NotNull
    @Pattern(regexp = "^\\([2-9][0-8][0-9]\\)\\s?[0-9]{3}\\-[0-9]{4}$",message = "please input correct phone number")
    @Column(name = "phone_number")
    private String phoneNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
