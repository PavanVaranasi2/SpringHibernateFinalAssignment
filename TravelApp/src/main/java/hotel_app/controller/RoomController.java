package hotel_app.controller;

import hotel_app.model.Hotel;
import hotel_app.model.Room;
import hotel_app.service.HotelService;
import hotel_app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private static final String ROOM_LIST_REDIRECT = "redirect:/rooms";

    private final RoomService roomService;
    private final HotelService hotelService;

    @Autowired
    public RoomController(RoomService roomService, HotelService hotelService) {
        this.hotelService = hotelService;
        this.roomService = roomService;
    }

    @GetMapping
    public String listAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "room-list";
    }

    @GetMapping("/{id}")
    public String viewRoomDetails(@PathVariable("id") int id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room-details";
        }
        return ROOM_LIST_REDIRECT;
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Room room = roomService.getRoomById(id);
        if (room != null) {
            model.addAttribute("room", room);
            return "room-form-update";
        }
        return ROOM_LIST_REDIRECT;
    }

    @PostMapping("/update")
    public String updateRoom(@ModelAttribute("room") Room updatedRoom, @ModelAttribute("availability") String availability) {
        Room existingRoom = roomService.getRoomById(updatedRoom.getId());
        if (existingRoom != null) {
            existingRoom.setRoomType(updatedRoom.getRoomType());
            existingRoom.setRoomNumber(updatedRoom.getRoomNumber());
            existingRoom.setPrice(updatedRoom.getPrice());
            existingRoom.setCapacity(updatedRoom.getCapacity());
            existingRoom.setFacilities(updatedRoom.getFacilities());
            existingRoom.setAvailability(availability.equalsIgnoreCase("true"));
            roomService.saveRoom(existingRoom);
        }

        return ROOM_LIST_REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") int id) {
        roomService.deleteRoom(id);
        return ROOM_LIST_REDIRECT;
    }

    @GetMapping("/create")
    public String showCreateRoomForm(Model model) {
        Room room = new Room();
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotels);
        return "room-form-create";
    }

    @PostMapping("/create")
    public String createRoom(@ModelAttribute("room") Room room, @ModelAttribute("hotelId") int hotelId) {
        Hotel hotel = hotelService.getHotelById(hotelId);
        room.setHotel(hotel);

        roomService.saveRoom(room);
        return ROOM_LIST_REDIRECT;
    }
}
