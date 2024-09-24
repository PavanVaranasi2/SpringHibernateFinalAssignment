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

    private static final String HOTELS_WITH_ROOMS_JSP = "hotelsWithRooms";
    private static final String ALL_TRAVELERS_LIST = "travelers";
    private static final String BOOKING_FINAL_FORM_CREATE_JSP = "booking-final-form-create";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

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
        model.addAttribute(HOTELS_WITH_ROOMS_JSP, hotelsWithRooms);
        model.addAttribute(ALL_TRAVELERS_LIST, travelers);
        model.addAttribute("bookingFinal", new BookingFinal());

        // Get today's date and format it for HTML input (yyyy-MM-dd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        model.addAttribute("todayDate", todayDate);

        return BOOKING_FINAL_FORM_CREATE_JSP;
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
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Check-in and check-out dates cannot be in the past.");

            // Repopulate travelers and hotelWithRooms data
            model.addAttribute(ALL_TRAVELERS_LIST, travelerService.getTravelers());
            model.addAttribute(HOTELS_WITH_ROOMS_JSP, hotelService.getAllHotelsWithRooms());

            return BOOKING_FINAL_FORM_CREATE_JSP;
        }

        if (checkInDate.after(checkOutDate)) {
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Invalid dates. Check-in date should be before check-out date.");

            model.addAttribute(ALL_TRAVELERS_LIST, travelerService.getTravelers());
            model.addAttribute(HOTELS_WITH_ROOMS_JSP, hotelService.getAllHotelsWithRooms());

            return BOOKING_FINAL_FORM_CREATE_JSP;
        }

        List<BookingFinal> existingBookings = bookingFinalService.getBookingsByRoom(roomId);
        for (BookingFinal booking : existingBookings) {
            if ((checkInDate.before(booking.getCheckOutDate())) && (checkOutDate.after(booking.getCheckInDate()))) {
                model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Room is not available for the selected dates.");

                model.addAttribute(ALL_TRAVELERS_LIST, travelerService.getTravelers());
                model.addAttribute(HOTELS_WITH_ROOMS_JSP, hotelService.getAllHotelsWithRooms());

                return BOOKING_FINAL_FORM_CREATE_JSP;
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
