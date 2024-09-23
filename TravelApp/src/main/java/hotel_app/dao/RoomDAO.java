package hotel_app.dao;

import hotel_app.model.Room;
import java.util.List;

public interface RoomDAO {
    List<Room> getAllRooms();
    void saveRoom(Room room);
    Room getRoomById(int id);
    void deleteRoom(int id);

    List<Room> getRoomsByHotel(int hotelId);
}
