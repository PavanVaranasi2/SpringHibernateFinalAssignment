package hotel_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "booking_final")
@Getter
@Setter
public class BookingFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Traveler cannot be null")
    @ManyToOne
    @JoinColumn(name = "traveler_id", nullable = false)
    private Traveler traveler;

    @NotNull(message = "Room cannot be null")
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull(message = "Check-in date is required")
    @Temporal(TemporalType.DATE)
    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @NotNull(message = "Check-out date is required")
    @Temporal(TemporalType.DATE)
    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    public BookingFinal() {
    }

    public BookingFinal(Date checkInDate, Date checkOutDate) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
