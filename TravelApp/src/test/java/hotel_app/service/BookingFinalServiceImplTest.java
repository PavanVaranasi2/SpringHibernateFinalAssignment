package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Room;
import hotel_app.model.Traveler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingFinalServiceImplTest {

    @Mock
    private BookingFinalDAO bookingFinalDAO;

    @InjectMocks
    private BookingFinalServiceImpl bookingFinalService;

    private Traveler traveler;
    private Room room;
    private BookingFinal booking;

    @Before
    public void setUp() {

        traveler = new Traveler("John Doe", "john@example.com", "1234567890");
        room = new Room("Deluxe", 101, 1500.0, 2, true, "WiFi, AC");
        booking = new BookingFinal();
        booking.setTraveler(traveler);
        booking.setRoom(room);
        booking.setCheckInDate(new Date());
        booking.setCheckOutDate(new Date(System.currentTimeMillis() + 86400000)); // 1 day later
    }

    @Test
    public void testCreateBooking() {
        bookingFinalService.createBooking(traveler, room, booking.getCheckInDate(), booking.getCheckOutDate());

        verify(bookingFinalDAO, times(1)).save(any(BookingFinal.class));
    }

    @Test
    public void testGetAllBookings() {
        List<BookingFinal> bookingsList = Collections.singletonList(booking);
        when(bookingFinalDAO.getAllBookings()).thenReturn(bookingsList);

        List<BookingFinal> result = bookingFinalService.getAllBookings();

        assertEquals(1, result.size());
        assertEquals(traveler, result.get(0).getTraveler());
        verify(bookingFinalDAO, times(1)).getAllBookings();
    }

    @Test
    public void testGetBookingsByTraveler() {
        List<BookingFinal> bookingsList = Collections.singletonList(booking);
        when(bookingFinalDAO.findByTravelerId(traveler.getId())).thenReturn(bookingsList);

        List<BookingFinal> result = bookingFinalService.getBookingsByTraveler(traveler.getId());

        assertEquals(1, result.size());
        verify(bookingFinalDAO, times(1)).findByTravelerId(traveler.getId());
    }

    @Test
    public void testGetBookingByRoom() {
        List<BookingFinal> bookingsList = Collections.singletonList(booking);
        when(bookingFinalDAO.findByRoomId(room.getId())).thenReturn(bookingsList);

        List<BookingFinal> result = bookingFinalService.getBookingByRoom(room.getId());

        assertEquals(1, result.size());
        verify(bookingFinalDAO, times(1)).findByRoomId(room.getId());
    }

    @Test
    public void testGetBookingsByRoom() {
        List<BookingFinal> bookingsList = Collections.singletonList(booking);
        when(bookingFinalDAO.findByRoomId(room.getId())).thenReturn(bookingsList);

        List<BookingFinal> result = bookingFinalService.getBookingsByRoom(room.getId());

        assertEquals(1, result.size());
        verify(bookingFinalDAO, times(1)).findByRoomId(room.getId());
    }

    @Test
    public void testDeleteBooking() {
        bookingFinalService.deleteBooking(booking.getId());

        verify(bookingFinalDAO, times(1)).deleteById(booking.getId());
    }

    @Test
    public void testGetBookingById() {
        when(bookingFinalDAO.getBookingById(booking.getId())).thenReturn(booking);
        BookingFinal result = bookingFinalService.getBookingById(booking.getId());

        assertNotNull(result);
        assertEquals(booking.getId(), result.getId());
        verify(bookingFinalDAO, times(1)).getBookingById(booking.getId());
    }
}
