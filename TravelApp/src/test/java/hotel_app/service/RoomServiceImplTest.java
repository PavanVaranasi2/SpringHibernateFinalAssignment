package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.dao.RoomDAO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Hotel;
import hotel_app.model.Room;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    @Mock
    private RoomDAO roomDAO;

    @Mock
    private BookingFinalDAO bookingFinalDAO;

    @InjectMocks
    private RoomServiceImpl roomService;

    private Room room;
    private Hotel hotel;
    private List<Room> roomList;

    @Before
    public void setUp() {

        hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel XYZ");

        room = new Room("Deluxe", 101, 150.0, 2, true, "WiFi");
        room.setId(1);
        room.setHotel(hotel);

        roomList = new ArrayList<>();
        roomList.add(room);
    }

    @Test
    public void testGetAllRooms() {
        when(roomDAO.getAllRooms()).thenReturn(roomList);

        List<Room> result = roomService.getAllRooms();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(room, result.get(0));

        verify(roomDAO, times(1)).getAllRooms();
    }

    @Test
    public void testSaveRoom() {
        roomService.saveRoom(room);

        verify(roomDAO, times(1)).saveRoom(room);
    }

    @Test
    public void testGetRoomById_RoomExists() {
        when(roomDAO.getRoomById(1)).thenReturn(room);

        Room result = roomService.getRoomById(1);

        assertNotNull(result);
        assertEquals(room, result);
        assertEquals("Hotel XYZ", result.getHotel().getName());

        verify(roomDAO, times(1)).getRoomById(1);
    }

    @Test
    public void testGetRoomById_RoomNotFound() {
        when(roomDAO.getRoomById(1)).thenReturn(null);

        Room result = roomService.getRoomById(1);

        assertNull(result);

        verify(roomDAO, times(1)).getRoomById(1);
    }

    @Test
    public void testDeleteRoom_NoBookings() {
        when(bookingFinalDAO.findByRoomId(1)).thenReturn(new ArrayList<>());

        roomService.deleteRoom(1);

        verify(bookingFinalDAO, times(1)).findByRoomId(1);
        verify(roomDAO, times(1)).deleteRoom(1);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteRoom_WithActiveBookings() {
        List<BookingFinal> bookings = new ArrayList<>();
        bookings.add(new BookingFinal());
        when(bookingFinalDAO.findByRoomId(1)).thenReturn(bookings);

        roomService.deleteRoom(1);
        verify(bookingFinalDAO, times(1)).findByRoomId(1);
        verify(roomDAO, never()).deleteRoom(1);
    }

    @Test
    public void testGetRoomsByHotel() {
        when(roomDAO.getRoomsByHotel(1)).thenReturn(roomList);

        List<Room> result = roomService.getRoomsByHotel(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(room, result.get(0));

        verify(roomDAO, times(1)).getRoomsByHotel(1);
    }

    @Test
    public void testDeleteRoom_NoBookings_EmptyList() {
        when(bookingFinalDAO.findByRoomId(1)).thenReturn(new ArrayList<>());

        roomService.deleteRoom(1);

        verify(roomDAO, times(1)).deleteRoom(1);
    }

    @Test
    public void testDeleteRoom_ExceptionThrown() {
        List<BookingFinal> bookings = new ArrayList<>();
        bookings.add(new BookingFinal());
        when(bookingFinalDAO.findByRoomId(1)).thenReturn(bookings);

        try {
            roomService.deleteRoom(1);
        } catch (RuntimeException e) {
            assertEquals("Room cannot be deleted as it has active bookings.", e.getMessage());
        }

        verify(roomDAO, never()).deleteRoom(1);
    }

    @Test
    public void testGetRoomById_RoomExists_HotelIsNull() {
        // Arrange
        Room roomWithoutHotel = new Room("Deluxe", 101, 150.0, 2, true, "WiFi");
        roomWithoutHotel.setId(1);

        when(roomDAO.getRoomById(1)).thenReturn(roomWithoutHotel);

        Room result = roomService.getRoomById(1);

        assertNotNull(result);
        assertNull(result.getHotel());

        verify(roomDAO, times(1)).getRoomById(1);
    }

}
