package hotel_app.dao;

import hotel_app.model.Traveler;

import java.util.List;

public interface TravelerDAO {
    public List<Traveler> getTravelers();
    public void saveTraveler(Traveler traveler);
    public Traveler getTraveler(int id);
    public void deleteTraveler(int id);

}
