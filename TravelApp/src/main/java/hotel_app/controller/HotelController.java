package hotel_app.controller;

import hotel_app.model.Hotel;
import hotel_app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private static final String HOTEL_ATTRIBUTE = "hotel";
    private static final String HOTEL_LIST_REDIRECT = "redirect:/hotels/";

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/")
    public String listAllHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("hotels", hotels);
        return "hotel-list";
    }

    @GetMapping("/list")
    public ModelAndView getAllHotelsWithRooms(Model model) {
        List<Hotel> hotels = hotelService.getAllHotelsWithRooms();

        ModelAndView modelAndView = new ModelAndView("hotel-rooms-modal");
        modelAndView.addObject("hotels", hotels);  // Pass hotels with rooms to the modal

        return modelAndView;
    }

    @GetMapping("/{id}")
    public String viewHotelDetails(@PathVariable("id") int id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute(HOTEL_ATTRIBUTE, hotel);
            model.addAttribute("rooms", hotel.getRooms());
            return "hotel-details";
        }
        return "redirect:/hotels";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute(HOTEL_ATTRIBUTE, hotel);
            return "hotel-form-update";
        }
        return HOTEL_LIST_REDIRECT;
    }

    @PostMapping("/update")
    public String updateHotel(@ModelAttribute(HOTEL_ATTRIBUTE) Hotel hotel) {
        hotelService.saveHotel(hotel);
        return HOTEL_LIST_REDIRECT;
    }

    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable("id") int id) {
        hotelService.deleteHotel(id);
        return HOTEL_LIST_REDIRECT;
    }

    @GetMapping("/create")
    public String showCreateHotelForm(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute(HOTEL_ATTRIBUTE, hotel);
        return "hotel-form-create";
    }

    @PostMapping("/create")
    public String createHotel(@ModelAttribute(HOTEL_ATTRIBUTE) Hotel hotel) {
        hotelService.saveHotel(hotel);
        return HOTEL_LIST_REDIRECT;
    }
}
