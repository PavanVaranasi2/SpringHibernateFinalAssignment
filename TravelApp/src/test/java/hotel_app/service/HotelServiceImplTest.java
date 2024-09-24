package hotel_app.service;

import hotel_app.dao.HotelDAO;
import hotel_app.model.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    @Mock
    private HotelDAO hotelDAO;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotels() {
        Hotel hotel1 = new Hotel("Hotel A", "Location A", "1234567890", "email@example.com", 5, "Great hotel", 10, "Wi-Fi, Pool");
        Hotel hotel2 = new Hotel("Hotel B", "Location B", "0987654321", "email2@example.com", 4, "Good hotel", 20, "Wi-Fi, Gym");
        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);
        when(hotelDAO.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelService.getAllHotels();

        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        assertEquals("Hotel B", result.get(1).getName());
        verify(hotelDAO, times(1)).getAllHotels();
    }

    @Test
    public void testSaveHotel() {
        Hotel hotel = new Hotel("Hotel A", "Location A", "1234567890", "email@example.com", 5, "Great hotel", 10, "Wi-Fi, Pool");

        hotelService.saveHotel(hotel);

        verify(hotelDAO, times(1)).saveHotel(hotel);
    }

    @Test
    public void testGetHotelById() {
        Hotel hotel = new Hotel("Hotel A", "Location A", "1234567890", "email@example.com", 5, "Great hotel", 10, "Wi-Fi, Pool");
        when(hotelDAO.getHotelById(1)).thenReturn(hotel);

        Hotel result = hotelService.getHotelById(1);

        assertEquals("Hotel A", result.getName());
        assertEquals("Location A", result.getLocation());
        verify(hotelDAO, times(1)).getHotelById(1);
    }

    // New test case for hotel not found
    @Test
    public void testGetHotelById_NotFound() {
        when(hotelDAO.getHotelById(1)).thenReturn(null);

        Hotel result = hotelService.getHotelById(1);

        assertNull(result);
        verify(hotelDAO, times(1)).getHotelById(1);
    }

    @Test
    public void testDeleteHotel() {
        hotelService.deleteHotel(1);

        verify(hotelDAO, times(1)).deleteHotel(1);
    }

    @Test
    public void testGetAllHotelsWithRooms() {
        Hotel hotel1 = new Hotel("Hotel A", "Location A", "1234567890", "email@example.com", 5, "Great hotel", 10, "Wi-Fi, Pool");
        Hotel hotel2 = new Hotel("Hotel B", "Location B", "0987654321", "email2@example.com", 4, "Good hotel", 20, "Wi-Fi, Gym");
        List<Hotel> hotelsWithRooms = Arrays.asList(hotel1, hotel2);
        when(hotelDAO.getHotelsWithRooms()).thenReturn(hotelsWithRooms);

        List<Hotel> result = hotelService.getAllHotelsWithRooms();

        assertEquals(2, result.size());
        verify(hotelDAO, times(1)).getHotelsWithRooms();
    }
}
