package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.dao.TravelerDAO;
import hotel_app.dto.TravelerDTO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Traveler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TravelerServiceImplTest {

    @Mock
    private TravelerDAO travelerDAO;

    @Mock
    private BookingFinalDAO bookingFinalDAO;

    @InjectMocks
    private TravelerServiceImpl travelerService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTravelers() {
        Traveler traveler1 = new Traveler("John Doe", "john@example.com", "1234567890");
        Traveler traveler2 = new Traveler("Jane Doe", "jane@example.com", "0987654321");
        List<Traveler> travelerList = Arrays.asList(traveler1, traveler2);
        when(travelerDAO.getTravelers()).thenReturn(travelerList);

        List<Traveler> result = travelerService.getTravelers();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
        verify(travelerDAO, times(1)).getTravelers();
    }

    @Test
    public void testSaveTraveler() {
        Traveler traveler = new Traveler("John Doe", "john@example.com", "1234567890");

        travelerService.saveTraveler(traveler);

        verify(travelerDAO, times(1)).saveTraveler(traveler);
    }

    @Test
    public void testGetTraveler() {
        Traveler traveler = new Traveler("John Doe", "john@example.com", "1234567890");
        when(travelerDAO.getTraveler(1)).thenReturn(traveler);

        Traveler result = travelerService.getTraveler(1);

        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        assertEquals("1234567890", result.getPhoneNumber());
        verify(travelerDAO, times(1)).getTraveler(1);
    }

    @Test
    public void testDeleteTravelerNoBookings() {
        when(bookingFinalDAO.findByTravelerId(1)).thenReturn(Collections.emptyList());

        travelerService.deleteTraveler(1);

        verify(bookingFinalDAO, times(1)).findByTravelerId(1);
        verify(travelerDAO, times(1)).deleteTraveler(1);
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteTravelerWithBookings() {
        BookingFinal booking = mock(BookingFinal.class);
        when(bookingFinalDAO.findByTravelerId(1)).thenReturn(Collections.singletonList(booking));

        travelerService.deleteTraveler(1);

        verify(travelerDAO, never()).deleteTraveler(1);
    }

    @Test
    public void testGetTravelerDTOs() {
        Traveler traveler1 = new Traveler("John Doe", "john@example.com", "1234567890");
        traveler1.setId(1);
        Traveler traveler2 = new Traveler("Jane Doe", "jane@example.com", "0987654321");
        traveler2.setId(2);

        when(travelerDAO.getTravelers()).thenReturn(Arrays.asList(traveler1, traveler2));

        List<TravelerDTO> result = travelerService.getTravelerDTOs();

        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("jane@example.com", result.get(1).getEmail());

        assertEquals("12******90", result.get(0).getPhoneNumber());
        assertEquals("09******21", result.get(1).getPhoneNumber());

        verify(travelerDAO, times(1)).getTravelers();
    }
}
