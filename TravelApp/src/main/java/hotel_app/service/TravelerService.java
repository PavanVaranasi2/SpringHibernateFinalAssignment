package hotel_app.service;

import hotel_app.dto.TravelerDTO;
import hotel_app.model.Traveler;

import java.util.List;

public interface TravelerService {
    List<Traveler> getTravelers();
    void saveTraveler(Traveler traveler);
    Traveler getTraveler(int id);
    void deleteTraveler(int id);

    // Returns list of TravelerDTO's to hide sensitive information.
    List<TravelerDTO> getTravelerDTOs();

}