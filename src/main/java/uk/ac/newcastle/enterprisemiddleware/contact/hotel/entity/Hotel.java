package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;

import javax.persistence.*;
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
    private String hotelName;
}
