package hotel_app.controller;

import hotel_app.model.BookingFinal;
import hotel_app.model.Hotel;
import hotel_app.model.Traveler;
import hotel_app.model.Room;
import hotel_app.service.BookingFinalService;
import hotel_app.service.HotelService;
import hotel_app.service.RoomService;
import hotel_app.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bookings/final")
public class BookingFinalController {

    private final static String hotelWithRoomsJsp = "hotelsWithRooms";
    private final static String allTravelersList = "travelers";
    private final static String bookingFinalFormCreateJSP = "booking-final-form-create";

    private final BookingFinalService bookingFinalService;
    private final TravelerService travelerService;
    private final RoomService roomService;
    private final HotelService hotelService;

    @Autowired
    public BookingFinalController(BookingFinalService bookingFinalService,
                                  TravelerService travelerService,
                                  RoomService roomService,
                                  HotelService hotelService) {
        this.bookingFinalService = bookingFinalService;
        this.travelerService = travelerService;
        this.roomService = roomService;
        this.hotelService = hotelService;
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Traveler> travelers = travelerService.getTravelers();
        List<Hotel> hotelsWithRooms = hotelService.getAllHotelsWithRooms();
        model.addAttribute(hotelWithRoomsJsp, hotelsWithRooms);
        model.addAttribute(allTravelersList, travelers);
        model.addAttribute("bookingFinal", new BookingFinal());

        // Get today's date and format it for HTML input (yyyy-MM-dd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        model.addAttribute("todayDate", todayDate);

        return bookingFinalFormCreateJSP;
    }

    @PostMapping("/create")
    public String createBooking(
            @RequestParam("travelerId") Integer travelerId,
            @RequestParam("roomId") Integer roomId,
            @RequestParam("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
            @RequestParam("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
            Model model) {

        Traveler traveler = travelerService.getTraveler(travelerId);
        Room room = roomService.getRoomById(roomId);

        Date today = new Date();
        if (checkInDate.before(today) || checkOutDate.before(today)) {
            model.addAttribute("errorMessage", "Check-in and check-out dates cannot be in the past.");

            // Repopulate travelers and hotelWithRooms data
            model.addAttribute(allTravelersList, travelerService.getTravelers());
            model.addAttribute(hotelWithRoomsJsp, hotelService.getAllHotelsWithRooms());

            return bookingFinalFormCreateJSP;
        }

        if (checkInDate.after(checkOutDate)) {
            model.addAttribute("errorMessage", "Invalid dates. Check-in date should be before check-out date.");

            model.addAttribute(allTravelersList, travelerService.getTravelers());
            model.addAttribute(hotelWithRoomsJsp, hotelService.getAllHotelsWithRooms());

            return bookingFinalFormCreateJSP;
        }

        List<BookingFinal> existingBookings = bookingFinalService.getBookingsByRoom(roomId);
        for (BookingFinal booking : existingBookings) {
            if ((checkInDate.before(booking.getCheckOutDate())) && (checkOutDate.after(booking.getCheckInDate()))) {
                model.addAttribute("errorMessage", "Room is not available for the selected dates.");

                model.addAttribute(allTravelersList, travelerService.getTravelers());
                model.addAttribute(hotelWithRoomsJsp, hotelService.getAllHotelsWithRooms());

                return bookingFinalFormCreateJSP;
            }
        }

        BookingFinal booking = new BookingFinal(checkInDate, checkOutDate);
        booking.setTraveler(traveler);
        booking.setRoom(room);
        bookingFinalService.createBooking(traveler, room, checkInDate, checkOutDate);

        return "redirect:/bookings/final";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") Integer id) {
        bookingFinalService.deleteBooking(id);
        return "redirect:/bookings/final";
    }

    @GetMapping
    public String listAllBookings(Model model) {
        List<BookingFinal> bookings = bookingFinalService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "booking-final-list";
    }

    @GetMapping("/{id}")
    public String viewBookingDetails(@PathVariable("id") Integer bookingId, Model model) {
        BookingFinal booking = bookingFinalService.getBookingById(bookingId);

        model.addAttribute("booking", booking);
        model.addAttribute("traveler", booking.getTraveler());
        model.addAttribute("room", booking.getRoom());
        model.addAttribute("hotel", booking.getRoom().getHotel());

        return "booking-final-details";
    }

}
