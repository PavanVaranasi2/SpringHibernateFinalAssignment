package hotel_app.dao;

import hotel_app.model.Hotel;
import java.util.List;

public interface HotelDAO {
    List<Hotel> getAllHotels();
    void saveHotel(Hotel hotel);
    Hotel getHotelById(int id);
    void deleteHotel(int id);

    List<Hotel> getHotelsWithRooms();
}
