package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.dao.RoomDAO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;
    private final BookingFinalDAO bookingFinalDAO;

    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO, BookingFinalDAO bookingFinalDAO) {
        this.bookingFinalDAO = bookingFinalDAO;
        this.roomDAO = roomDAO;
    }

    @Override
    @Transactional
    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    @Override
    @Transactional
    public void saveRoom(Room room) {
        roomDAO.saveRoom(room);
    }

    @Override
    @Transactional
    public Room getRoomById(int id) {
        Room room = roomDAO.getRoomById(id);

        if (room != null && room.getHotel() != null) {
            room.getHotel().getName();
        }

        return room;
    }

    @Override
    @Transactional
    public void deleteRoom(int id) {
        List<BookingFinal> bookings = bookingFinalDAO.findByRoomId(id);

        if (!bookings.isEmpty()) {
            throw new IllegalStateException("Room cannot be deleted as it has active bookings.");
        }

        roomDAO.deleteRoom(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getRoomsByHotel(int hotelId) {
        return roomDAO.getRoomsByHotel(hotelId);
    }
}
