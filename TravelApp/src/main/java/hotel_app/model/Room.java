package hotel_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "room")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "room_type", nullable = false)
    private String roomType;

    @Column(name = "room_number", nullable = false)
    private int roomNumber;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "capacity", nullable = false)
    private int capacity;

    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Column(name = "facilities")
    private String facilities;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<BookingFinal> bookings;

    public Room() {

    }

    public Room(String roomType, int roomNumber, double price, int capacity, boolean availability, String facilities) {
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.price = price;
        this.capacity = capacity;
        this.availability = availability;
        this.facilities = facilities;
    }
}
