package hotel_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "traveler")
@Getter
@Setter
public class Traveler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Email(message = "Email should be valid and contain '@' and '.'")
    @Column(name = "email")
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "traveler", cascade = CascadeType.ALL)
    private List<BookingFinal> bookings;

    public Traveler() {

    }

    public Traveler(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Traveler{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
