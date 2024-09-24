package hotel_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "reception_contact_number", nullable = false)
    private String receptionContactNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "star_rating")
    private int starRating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "total_rooms")
    private int totalRooms;

    @Column(name = "amenities")
    private String amenities;

    // One-to-many relationship with Room (to be implemented later)
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Room> rooms;

    public Hotel() {}

    public Hotel(String name, String location, String receptionContactNumber, String email, int starRating, String description, int totalRooms, String amenities) {
        this.name = name;
        this.location = location;
        this.receptionContactNumber = receptionContactNumber;
        this.email = email;
        this.starRating = starRating;
        this.description = description;
        this.totalRooms = totalRooms;
        this.amenities = amenities;
    }
}
