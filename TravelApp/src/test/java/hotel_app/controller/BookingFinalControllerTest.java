package hotel_app.controller;

import hotel_app.model.BookingFinal;
import hotel_app.model.Hotel;
import hotel_app.model.Room;
import hotel_app.model.Traveler;
import hotel_app.service.BookingFinalService;
import hotel_app.service.HotelService;
import hotel_app.service.RoomService;
import hotel_app.service.TravelerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingFinalControllerTest {

    @InjectMocks
    private BookingFinalController bookingFinalController;

    @Mock
    private BookingFinalService bookingFinalService;

    @Mock
    private TravelerService travelerService;

    @Mock
    private RoomService roomService;

    @Mock
    private HotelService hotelService;

    @Mock
    private Model model;

    private List<Traveler> travelers;
    private List<Hotel> hotelsWithRooms;
    private Traveler traveler;
    private Room room;
    private Date todayDate;

    @Before
    public void setUp() throws Exception {
        traveler = new Traveler();
        traveler.setId(1);
        traveler.setName("Tim David");
        traveler.setEmail("tim.david@gmail.com");

        room = new Room();
        room.setId(1);

        travelers = List.of(traveler);

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Test Hotel");
        hotel.setRooms(List.of(room));

        hotelsWithRooms = List.of(hotel);

        todayDate = new Date();

        when(travelerService.getTravelers()).thenReturn(travelers);
        when(hotelService.getAllHotelsWithRooms()).thenReturn(hotelsWithRooms);
    }


    @Test
    public void testShowCreateForm() {
        String view = bookingFinalController.showCreateForm(model);

        verify(model).addAttribute("hotelsWithRooms", hotelsWithRooms);
        verify(model).addAttribute("travelers", travelers);
        verify(model).addAttribute(eq("bookingFinal"), any(BookingFinal.class));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDate = dateFormat.format(todayDate);
        verify(model).addAttribute("todayDate", expectedDate);

        assertEquals("booking-final-form-create", view);
    }


    @Test
    public void testCreateBooking_InvalidCheckInAfterCheckOut() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(2024, Calendar.SEPTEMBER, 20);
        Date checkInDate = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 19);
        Date checkOutDate = calendar.getTime();

        BookingFinal request = new BookingFinal();

        bookingFinalController.createBooking(1, 1, checkInDate, checkOutDate, model);

        verify(model).addAttribute("errorMessage", "Check-in and check-out dates cannot be in the past.");
    }

    @Test
    public void testDeleteBooking() {
        String view = bookingFinalController.deleteBooking(1);

        verify(bookingFinalService).deleteBooking(1);

        assertEquals("redirect:/bookings/final", view);
    }

    @Test
    public void testListAllBookings() {
        List<BookingFinal> bookings = List.of(new BookingFinal());
        when(bookingFinalService.getAllBookings()).thenReturn(bookings);

        String view = bookingFinalController.listAllBookings(model);

        verify(model).addAttribute("bookings", bookings);

        assertEquals("booking-final-list", view);
    }

    @Test
    public void testViewBookingDetails() {
        BookingFinal booking = new BookingFinal();
        booking.setTraveler(traveler);
        booking.setRoom(room);
        when(bookingFinalService.getBookingById(anyInt())).thenReturn(booking);

        String view = bookingFinalController.viewBookingDetails(1, model);

        verify(model).addAttribute("booking", booking);
        verify(model).addAttribute("traveler", traveler);
        verify(model).addAttribute("room", room);
        verify(model).addAttribute("hotel", room.getHotel());

        assertEquals("booking-final-details", view);
    }

    @Test
    public void testCreateBooking_Success() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L);
        Date checkOutDate = new Date(System.currentTimeMillis() + 2 * 86400000L);

        Traveler mockTraveler = new Traveler();
        mockTraveler.setId(travelerId);
        mockTraveler.setName("Tim David");
        mockTraveler.setEmail("tim.david@gmail.com");

        Room mockRoom = new Room();
        mockRoom.setId(roomId);

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.emptyList());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService).createBooking(mockTraveler, mockRoom, checkInDate, checkOutDate);

        assertEquals("redirect:/bookings/final", result);
    }

    @Test
    public void testCreateBooking_InvalidDateRange() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 2 * 86400000L);
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000L);

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Invalid dates. Check-in date should be before check-out date.");
    }

    @Test
    public void testCreateBooking_ValidFutureDates() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 3 * 86400000L);
        Date checkOutDate = new Date(System.currentTimeMillis() + 4 * 86400000L);

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.emptyList());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService).createBooking(mockTraveler, mockRoom, checkInDate, checkOutDate);
        assertEquals("redirect:/bookings/final", result);
    }

    @Test
    public void testCreateBooking_RoomPartiallyUnavailable() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L);
        Date checkOutDate = new Date(System.currentTimeMillis() + 5 * 86400000L);

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        // Existing booking ends during the new booking period
        BookingFinal existingBooking = new BookingFinal(new Date(System.currentTimeMillis() + 2 * 86400000L), new Date(System.currentTimeMillis() + 3 * 86400000L));
        existingBooking.setRoom(mockRoom);

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.singletonList(existingBooking));

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));
        verify(model).addAttribute("errorMessage", "Room is not available for the selected dates.");
        assertEquals("booking-final-form-create", result);
    }

    @Test
    public void testCreateBooking_PastCheckInDate() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() - 86400000);
        Date checkOutDate = new Date(today.getTime() + 86400000);

        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Check-in and check-out dates cannot be in the past.");
    }

    @Test
    public void testCreateBooking_PastCheckOutDate() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() + 86400000);
        Date checkOutDate = new Date(today.getTime() - 86400000);

        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Check-in and check-out dates cannot be in the past.");
    }

    @Test
    public void testCreateBooking_CheckInAfterCheckOut() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 172800000);
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000);

        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Invalid dates. Check-in date should be before check-out date.");
    }

    @Test
    public void testCreateBooking_RoomUnavailable() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000);
        Date checkOutDate = new Date(System.currentTimeMillis() + 259200000);

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();
        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        BookingFinal existingBooking = new BookingFinal();
        existingBooking.setCheckInDate(new Date(System.currentTimeMillis() + 172800000));
        existingBooking.setCheckOutDate(new Date(System.currentTimeMillis() + 345600000));
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.singletonList(existingBooking));

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Room is not available for the selected dates.");
    }

    @Test
    public void testCreateBooking_PastDates() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() - 86400000);
        Date checkOutDate = new Date(today.getTime() + 86400000);

        // Mock the traveler and room data
        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Check-in and check-out dates cannot be in the past.");
    }

    @Test
    public void testCreateBooking_OverlappingBookings() {
        Integer travelerId = 1;
        Integer roomId = 1;

        // Create dates for the new booking
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L);
        Date checkOutDate = new Date(System.currentTimeMillis() + 3 * 86400000L);

        BookingFinal existingBooking1 = new BookingFinal(
                new Date(System.currentTimeMillis() + 86400000L),
                new Date(System.currentTimeMillis() + 2 * 86400000L)
        );
        BookingFinal existingBooking2 = new BookingFinal(
                new Date(System.currentTimeMillis() + 2 * 86400000L),
                new Date(System.currentTimeMillis() + 4 * 86400000L)
        );
        existingBooking1.setRoom(new Room());
        existingBooking1.getRoom().setId(roomId);
        existingBooking2.setRoom(new Room());
        existingBooking2.getRoom().setId(roomId);

        Traveler mockTraveler = new Traveler();
        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);

        Room mockRoom = new Room();
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Arrays.asList(existingBooking1, existingBooking2));

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        assertEquals("booking-final-form-create", result);

        verify(model).addAttribute("errorMessage", "Room is not available for the selected dates.");
        verify(model).addAttribute(eq("travelers"), any());
        verify(model).addAttribute(eq("hotelsWithRooms"), any());
    }


}
