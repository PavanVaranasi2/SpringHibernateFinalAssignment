package hotel_app.service;

import hotel_app.dao.HotelDAO;
import hotel_app.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Override
    @Transactional
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    @Override
    @Transactional
    public void saveHotel(Hotel hotel) {
        hotelDAO.saveHotel(hotel);
    }

    @Override
    @Transactional
    public Hotel getHotelById(int id) {
        Hotel hotel = hotelDAO.getHotelById(id);

//        if (hotel.getRooms() != null) {
//            hotel.getRooms().size();
//        }

        return hotel;
    }

    @Override
    @Transactional
    public void deleteHotel(int id) {
        hotelDAO.deleteHotel(id);
    }

    @Override
    @Transactional
    public List<Hotel> getAllHotelsWithRooms() {
        return hotelDAO.getHotelsWithRooms();
    }
}
