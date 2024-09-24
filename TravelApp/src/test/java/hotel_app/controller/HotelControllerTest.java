package hotel_app.controller;

import hotel_app.model.Hotel;
import hotel_app.service.HotelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HotelControllerTest {

    @InjectMocks
    private HotelController hotelController;

    @Mock
    private HotelService hotelService;

    @Mock
    private Model model;

    private Hotel hotel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Hotel Plaza");
        hotel.setLocation("New York");
        hotel.setReceptionContactNumber("1234567890");
        hotel.setEmail("info@hotelplaza.com");
        hotel.setStarRating(5);
        hotel.setDescription("A luxury hotel in New York.");
        hotel.setTotalRooms(100);
        hotel.setAmenities("Wi-Fi, Pool, Spa");
    }

    @Test
    public void testListAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);
        when(hotelService.getAllHotels()).thenReturn(hotels);

        String viewName = hotelController.listAllHotels(model);

        verify(hotelService, times(1)).getAllHotels();
        verify(model, times(1)).addAttribute("hotels", hotels);
        assertEquals("hotel-list", viewName);
    }

    @Test
    public void testGetAllHotelsWithRooms() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);
        when(hotelService.getAllHotelsWithRooms()).thenReturn(hotels);

        ModelAndView modelAndView = hotelController.getAllHotelsWithRooms(model);

        verify(hotelService, times(1)).getAllHotelsWithRooms();
        assertEquals("hotel-rooms-modal", modelAndView.getViewName());
        assertEquals(hotels, modelAndView.getModel().get("hotels"));
    }

    @Test
    public void testViewHotelDetails() {
        when(hotelService.getHotelById(1)).thenReturn(hotel);

        String viewName = hotelController.viewHotelDetails(1, model);

        verify(hotelService, times(1)).getHotelById(1);
        verify(model, times(1)).addAttribute("hotel", hotel);
        verify(model, times(1)).addAttribute("rooms", hotel.getRooms());
        assertEquals("hotel-details", viewName);
    }

    @Test
    public void testViewHotelDetails_HotelNotFound() {
        when(hotelService.getHotelById(1)).thenReturn(null);

        String viewName = hotelController.viewHotelDetails(1, model);

        verify(hotelService, times(1)).getHotelById(1);
        assertEquals("redirect:/hotels", viewName);
    }

    @Test
    public void testShowUpdateForm() {
        when(hotelService.getHotelById(1)).thenReturn(hotel);

        String viewName = hotelController.showUpdateForm(1, model);

        verify(hotelService, times(1)).getHotelById(1);
        verify(model, times(1)).addAttribute("hotel", hotel);
        assertEquals("hotel-form-update", viewName);
    }

    @Test
    public void testShowUpdateForm_HotelNotFound() {
        when(hotelService.getHotelById(1)).thenReturn(null);

        String viewName = hotelController.showUpdateForm(1, model);

        verify(hotelService, times(1)).getHotelById(1);
        assertEquals("redirect:/hotels/", viewName);
    }

    @Test
    public void testUpdateHotel() {
        String redirectUrl = hotelController.updateHotel(hotel);

        verify(hotelService, times(1)).saveHotel(hotel);
        assertEquals("redirect:/hotels/", redirectUrl);
    }

    @Test
    public void testDeleteHotel() {
        String redirectUrl = hotelController.deleteHotel(1);

        verify(hotelService, times(1)).deleteHotel(1);
        assertEquals("redirect:/hotels/", redirectUrl);
    }

    @Test
    public void testShowCreateHotelForm() {
        String viewName = hotelController.showCreateHotelForm(model);

        verify(model, times(1)).addAttribute(eq("hotel"), any(Hotel.class));
        assertEquals("hotel-form-create", viewName);
    }

    @Test
    public void testCreateHotel() {
        String redirectUrl = hotelController.createHotel(hotel);

        verify(hotelService, times(1)).saveHotel(hotel);
        assertEquals("redirect:/hotels/", redirectUrl);
    }
}
