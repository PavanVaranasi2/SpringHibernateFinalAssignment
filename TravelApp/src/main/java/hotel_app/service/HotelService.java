package hotel_app.service;

import hotel_app.model.Hotel;
import java.util.List;

public interface HotelService {
    List<Hotel> getAllHotels();
    void saveHotel(Hotel hotel);
    Hotel getHotelById(int id);
    void deleteHotel(int id);

    List<Hotel> getAllHotelsWithRooms();
}
