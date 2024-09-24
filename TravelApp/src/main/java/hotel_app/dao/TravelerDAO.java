package hotel_app.dao;

import hotel_app.model.Traveler;

import java.util.List;

public interface TravelerDAO {
    List<Traveler> getTravelers();
    void saveTraveler(Traveler traveler);
    Traveler getTraveler(int id);
    void deleteTraveler(int id);

}
