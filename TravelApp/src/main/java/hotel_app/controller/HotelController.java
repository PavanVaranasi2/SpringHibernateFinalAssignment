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
            model.addAttribute("hotel", hotel);
            model.addAttribute("rooms", hotel.getRooms());
            return "hotel-details";
        }
        return "redirect:/hotels";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
            return "hotel-form-update";
        }
        return "redirect:/hotels/";
    }

    @PostMapping("/update")
    public String updateHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels/";
    }

    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable("id") int id) {
        hotelService.deleteHotel(id);
        return "redirect:/hotels/";
    }

    @GetMapping("/create")
    public String showCreateHotelForm(Model model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        return "hotel-form-create";
    }

    @PostMapping("/create")
    public String createHotel(@ModelAttribute("hotel") Hotel hotel) {
        hotelService.saveHotel(hotel);
        return "redirect:/hotels/";
    }
}
