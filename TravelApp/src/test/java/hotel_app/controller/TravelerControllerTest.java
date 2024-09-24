package hotel_app.controller;

import hotel_app.dto.TravelerDTO;
import hotel_app.model.Traveler;
import hotel_app.service.TravelerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TravelerControllerTest {

    @InjectMocks
    private TravelerController travelerController;

    @Mock
    private TravelerService travelerService;

    @Mock
    private Model model;

    private Traveler traveler;

    private TravelerDTO travelerDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        traveler = new Traveler();
        traveler.setId(1);
        traveler.setName("John Doe");
        traveler.setEmail("john.doe@example.com");
        traveler.setPhoneNumber("1234567890");

        travelerDTO = new TravelerDTO();
        travelerDTO.setId(1);
        travelerDTO.setName("John Doe");
        travelerDTO.setEmail("john.doe@example.com");
        travelerDTO.setPhoneNumber("12******90");
    }

    @Test
    public void testListTravelers() {
        List<TravelerDTO> travelers = new ArrayList<>();
        travelers.add(travelerDTO);
        when(travelerService.getTravelerDTOs()).thenReturn(travelers);

        String viewName = travelerController.listTravelers(model);

        verify(travelerService, times(1)).getTravelerDTOs();
        verify(model, times(1)).addAttribute("travelers", travelers);
        assertEquals("traveler-list", viewName);
    }

    @Test
    public void testShowFormForAdd() {
        String viewName = travelerController.showFormForAdd(model);

        verify(model, times(1)).addAttribute(eq("traveler"), any(Traveler.class));
        assertEquals("traveler-form", viewName);
    }

    @Test
    public void testSaveTraveler() {
        String redirectUrl = travelerController.saveTraveler(traveler);

        verify(travelerService, times(1)).saveTraveler(traveler);
        assertEquals("redirect:/traveler/list", redirectUrl);
    }

    @Test
    public void testTravelerDetails() {
        when(travelerService.getTraveler(1)).thenReturn(traveler);

        String viewName = travelerController.travelerDetails(1, model);

        verify(travelerService, times(1)).getTraveler(1);
        verify(model, times(1)).addAttribute(eq("traveler"), any(TravelerDTO.class));
        assertEquals("traveler-details", viewName);
    }

    @Test
    public void testShowFormForUpdate() {
        when(travelerService.getTraveler(1)).thenReturn(traveler);

        String viewName = travelerController.showFormForUpdate(1, model);

        verify(travelerService, times(1)).getTraveler(1);
        verify(model, times(1)).addAttribute("traveler", traveler);
        assertEquals("traveler-form", viewName);
    }

    @Test
    public void testDeleteTraveler() {
        String redirectUrl = travelerController.deleteTraveler(1);

        verify(travelerService, times(1)).deleteTraveler(1);
        assertEquals("redirect:/traveler/list", redirectUrl);
    }
}
