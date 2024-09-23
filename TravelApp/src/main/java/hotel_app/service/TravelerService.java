package hotel_app.service;

import hotel_app.dto.TravelerDTO;
import hotel_app.model.Traveler;

import java.util.List;

public interface TravelerService {
    public List<Traveler> getTravelers();
    public void saveTraveler(Traveler traveler);
    public Traveler getTraveler(int id);
    public void deleteTraveler(int id);

    // Returns list of TravelerDTO's to hide sensitive information.
    public List<TravelerDTO> getTravelerDTOs();

}