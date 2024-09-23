package hotel_app.service;

import hotel_app.model.Room;
import java.util.List;

public interface RoomService {
    List<Room> getAllRooms();
    void saveRoom(Room room);
    Room getRoomById(int roomId);
    void deleteRoom(int roomId);

    List<Room> getRoomsByHotel(int hotelId);
}
