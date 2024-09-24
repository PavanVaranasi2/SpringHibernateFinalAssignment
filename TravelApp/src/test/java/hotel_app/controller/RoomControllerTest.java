package hotel_app.controller;

import hotel_app.model.Hotel;
import hotel_app.model.Room;
import hotel_app.service.HotelService;
import hotel_app.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @Mock
    private HotelService hotelService;

    @Mock
    private Model model;

    @InjectMocks
    private RoomController roomController;

    private Room room;
    private Hotel hotel;

    @Before
    public void setUp() {
        hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Test Hotel");

        room = new Room();
        room.setId(1);
        room.setRoomType("Single");
        room.setRoomNumber(101);
        room.setPrice(100.0);
        room.setCapacity(2);
        room.setAvailability(true);
        room.setHotel(hotel);
    }

    @Test
    public void testListAllRooms() {
        List<Room> rooms = Collections.singletonList(room);
        when(roomService.getAllRooms()).thenReturn(rooms);

        String viewName = roomController.listAllRooms(model);

        verify(roomService, times(1)).getAllRooms();
        verify(model, times(1)).addAttribute("rooms", rooms);
        assertEquals("room-list", viewName);
    }

    @Test
    public void testViewRoomDetails_RoomExists() {
        when(roomService.getRoomById(1)).thenReturn(room);

        String viewName = roomController.viewRoomDetails(1, model);

        verify(roomService, times(1)).getRoomById(1);
        verify(model, times(1)).addAttribute("room", room);
        assertEquals("room-details", viewName);
    }

    @Test
    public void testViewRoomDetails_RoomNotFound() {
        when(roomService.getRoomById(1)).thenReturn(null);

        String viewName = roomController.viewRoomDetails(1, model);

        verify(roomService, times(1)).getRoomById(1);
        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testShowUpdateForm_RoomExists() {
        when(roomService.getRoomById(1)).thenReturn(room);

        String viewName = roomController.showUpdateForm(1, model);

        verify(roomService, times(1)).getRoomById(1);
        verify(model, times(1)).addAttribute("room", room);
        assertEquals("room-form-update", viewName);
    }

    @Test
    public void testShowUpdateForm_RoomNotFound() {
        when(roomService.getRoomById(1)).thenReturn(null);

        String viewName = roomController.showUpdateForm(1, model);

        verify(roomService, times(1)).getRoomById(1);
        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testUpdateRoom_RoomExists_TrueAvailability() {
        when(roomService.getRoomById(1)).thenReturn(room);

        Room updatedRoom = new Room();
        updatedRoom.setId(1);
        updatedRoom.setRoomType("Double");
        updatedRoom.setRoomNumber(102);
        updatedRoom.setPrice(150.0);
        updatedRoom.setCapacity(3);

        String viewName = roomController.updateRoom(updatedRoom, "true");

        verify(roomService, times(1)).getRoomById(1);
        verify(roomService, times(1)).saveRoom(room);

        assertEquals("Double", room.getRoomType());
        assertEquals(102, room.getRoomNumber());
        assertEquals(150.0, room.getPrice(), 0.01);
        assertEquals(3, room.getCapacity());
        assertTrue(room.isAvailability());
        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testUpdateRoom_RoomExists_FalseAvailability() {
        when(roomService.getRoomById(1)).thenReturn(room);

        Room updatedRoom = new Room();
        updatedRoom.setId(1);
        updatedRoom.setRoomType("Double");
        updatedRoom.setRoomNumber(102);
        updatedRoom.setPrice(150.0);
        updatedRoom.setCapacity(3);

        String viewName = roomController.updateRoom(updatedRoom, "false");

        verify(roomService, times(1)).getRoomById(1);
        verify(roomService, times(1)).saveRoom(room);

        assertEquals("Double", room.getRoomType());
        assertEquals(102, room.getRoomNumber());
        assertEquals(150.0, room.getPrice(), 0.01);
        assertEquals(3, room.getCapacity());
        assertFalse(room.isAvailability());
        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testUpdateRoom_RoomNotFound() {
        when(roomService.getRoomById(1)).thenReturn(null);

        Room updatedRoom = new Room();
        updatedRoom.setId(1);

        String viewName = roomController.updateRoom(updatedRoom, "true");

        verify(roomService, times(1)).getRoomById(1);
        verify(roomService, never()).saveRoom(any(Room.class));

        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testDeleteRoom() {
        String viewName = roomController.deleteRoom(1);

        verify(roomService, times(1)).deleteRoom(1);
        assertEquals("redirect:/rooms", viewName);
    }

    @Test
    public void testShowCreateRoomForm() {
        List<Hotel> hotels = Collections.singletonList(hotel);
        when(hotelService.getAllHotels()).thenReturn(hotels);

        String viewName = roomController.showCreateRoomForm(model);

        verify(hotelService, times(1)).getAllHotels();
        verify(model, times(1)).addAttribute(eq("room"), any(Room.class));
        verify(model, times(1)).addAttribute("hotels", hotels);
        assertEquals("room-form-create", viewName);
    }

    @Test
    public void testCreateRoom() {
        when(hotelService.getHotelById(1)).thenReturn(hotel);

        Room newRoom = new Room();
        newRoom.setRoomType("Double");
        newRoom.setRoomNumber(102);
        newRoom.setPrice(150.0);
        newRoom.setCapacity(3);

        String viewName = roomController.createRoom(newRoom, 1);

        verify(hotelService, times(1)).getHotelById(1);
        verify(roomService, times(1)).saveRoom(newRoom);
        assertEquals("redirect:/rooms", viewName);
    }
}
