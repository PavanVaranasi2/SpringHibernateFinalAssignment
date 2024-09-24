package hotel_app.controller;

import hotel_app.dto.TravelerDTO;
import hotel_app.model.Traveler;
import hotel_app.service.TravelerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/traveler")
public class TravelerController {

    private static final String TRAVELER_ATTRIBUTE = "traveler";

    private final TravelerService travelerService;

    @Autowired
    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    @GetMapping("/list")
    public String listTravelers(Model model) {
        List<TravelerDTO> travelerDTOs = travelerService.getTravelerDTOs();
        model.addAttribute("travelers", travelerDTOs);
        return "traveler-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Traveler traveler = new Traveler();
        model.addAttribute(TRAVELER_ATTRIBUTE, traveler);
        return "traveler-form";
    }

    @PostMapping("/saveTraveler")
    public String saveTraveler(@ModelAttribute(TRAVELER_ATTRIBUTE) Traveler traveler) {
        travelerService.saveTraveler(traveler);
        return "redirect:/traveler/list";
    }

    @GetMapping("/details/{id}")
    public String travelerDetails(@PathVariable int id, Model model) {
        Traveler traveler = travelerService.getTraveler(id);

        TravelerDTO travelerDTO = new TravelerDTO(
                traveler.getId(),
                traveler.getName(),
                traveler.getEmail(),
                traveler.getPhoneNumber()
        );

        model.addAttribute(TRAVELER_ATTRIBUTE, travelerDTO);
        return "traveler-details";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable int id, Model model) {
        Traveler traveler = travelerService.getTraveler(id);
        model.addAttribute(TRAVELER_ATTRIBUTE, traveler);
        return "traveler-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTraveler(@PathVariable int id) {
        travelerService.deleteTraveler(id);
        return "redirect:/traveler/list";
    }

}
