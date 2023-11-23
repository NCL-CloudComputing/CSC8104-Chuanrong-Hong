package uk.ac.newcastle.enterprisemiddleware.contact.hotel.entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@NamedQueries({
})
@XmlRootElement
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @Size(min=1,max = 50)
    @Pattern(regexp ="^[A-Za-z]+$",message = "Please use a name without numbers or specials")
    @Column(name = "name")
    private String name;

}
