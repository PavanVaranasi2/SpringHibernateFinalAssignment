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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

        travelers = Arrays.asList(traveler);

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("Test Hotel");
        hotel.setRooms(Arrays.asList(room));

        hotelsWithRooms = Arrays.asList(hotel);

        todayDate = new Date();

        when(travelerService.getTravelers()).thenReturn(travelers);
        when(hotelService.getAllHotelsWithRooms()).thenReturn(hotelsWithRooms);
    }


    @Test
    public void testShowCreateForm() {
        String view = bookingFinalController.showCreateForm(model);

        verify(model).addAttribute(eq("hotelsWithRooms"), eq(hotelsWithRooms));
        verify(model).addAttribute(eq("travelers"), eq(travelers));
        verify(model).addAttribute(eq("bookingFinal"), any(BookingFinal.class));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String expectedDate = dateFormat.format(todayDate);
        verify(model).addAttribute(eq("todayDate"), eq(expectedDate));

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

//    @Test
//    public void testCreateBooking_RoomUnavailable() {
//        // Mocked inputs
//        Integer travelerId = 1;
//        Integer roomId = 1;
//        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L); // 1 day after today
//        Date checkOutDate = new Date(System.currentTimeMillis() + 2 * 86400000L); // 2 days after today
//
//        // Mocked traveler, room, and existing bookings
//        Traveler mockTraveler = new Traveler();
//        mockTraveler.setId(travelerId);
//
//        Room mockRoom = new Room();
//        mockRoom.setId(roomId);
//
//        BookingFinal existingBooking = new BookingFinal(new Date(), new Date(System.currentTimeMillis() + 3 * 86400000L)); // overlapping booking
//        existingBooking.setRoom(mockRoom);
//
//        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
//        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
//        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.singletonList(existingBooking));
//
//        // Call the controller method with overlapping dates
//        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);
//
//        // Verify no booking creation
//        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));
//
//        // Assert that the correct view is returned
//        assertEquals("booking-final-form-create", result);
//
//        // Verify error message is set in the model
//        verify(model).addAttribute(eq("errorMessage"), eq("Room is not available for the selected dates."));
//    }

    @Test
    public void testDeleteBooking() {
        String view = bookingFinalController.deleteBooking(1);

        verify(bookingFinalService).deleteBooking(1);

        assertEquals("redirect:/bookings/final", view);
    }

    @Test
    public void testListAllBookings() {
        List<BookingFinal> bookings = Arrays.asList(new BookingFinal());
        when(bookingFinalService.getAllBookings()).thenReturn(bookings);

        String view = bookingFinalController.listAllBookings(model);

        verify(model).addAttribute(eq("bookings"), eq(bookings));

        assertEquals("booking-final-list", view);
    }

    @Test
    public void testViewBookingDetails() {
        BookingFinal booking = new BookingFinal();
        booking.setTraveler(traveler);
        booking.setRoom(room);
        when(bookingFinalService.getBookingById(anyInt())).thenReturn(booking);

        String view = bookingFinalController.viewBookingDetails(1, model);

        verify(model).addAttribute(eq("booking"), eq(booking));
        verify(model).addAttribute(eq("traveler"), eq(traveler));
        verify(model).addAttribute(eq("room"), eq(room));
        verify(model).addAttribute(eq("hotel"), eq(room.getHotel()));

        assertEquals("booking-final-details", view);
    }

    @Test
    public void testCreateBooking_Success() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L); // 1 day after today
        Date checkOutDate = new Date(System.currentTimeMillis() + 2 * 86400000L); // 2 days after today

        // Mocked traveler and room objects
        Traveler mockTraveler = new Traveler();
        mockTraveler.setId(travelerId);
        mockTraveler.setName("Tim David");
        mockTraveler.setEmail("tim.david@gmail.com");

        Room mockRoom = new Room();
        mockRoom.setId(roomId);

        // Mock the service method calls
        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.emptyList()); // no existing bookings

        // Call the controller method
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify interactions
        verify(bookingFinalService).createBooking(mockTraveler, mockRoom, checkInDate, checkOutDate);

        // Assertions
        assertEquals("redirect:/bookings/final", result);
    }

//    @Test
//    public void testCreateBooking_PastCheckInDate() {
//        // Mocked inputs
//        Integer travelerId = 1;
//        Integer roomId = 1;
//        Date checkInDate = new Date(System.currentTimeMillis() - 86400000L); // 1 day before today
//        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000L); // 1 day after today
//
//        // Mock the traveler and room data
//        Traveler mockTraveler = new Traveler();
//        mockTraveler.setId(travelerId);
//
//        Room mockRoom = new Room();
//        mockRoom.setId(roomId);
//
//        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
//        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
//
//        // Call the controller method with a past check-in date
//        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);
//
//        // Verify no booking creation
//        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));
//
//        // Assert that the correct view is returned
//        assertEquals("booking-final-form-create", result);
//
//        // Verify error message is set in the model
//        verify(model).addAttribute(eq("errorMessage"), eq("Check-in and check-out dates cannot be in the past."));
//    }

    @Test
    public void testCreateBooking_InvalidDateRange() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 2 * 86400000L); // 2 days after today
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000L); // 1 day after today

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        // Call the controller method with invalid date range
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Invalid dates. Check-in date should be before check-out date."));
    }

    @Test
    public void testCreateBooking_ValidFutureDates() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 3 * 86400000L); // 3 days from today
        Date checkOutDate = new Date(System.currentTimeMillis() + 4 * 86400000L); // 4 days from today

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.emptyList());

        // Call the controller method with valid dates
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        verify(bookingFinalService).createBooking(mockTraveler, mockRoom, checkInDate, checkOutDate);
        assertEquals("redirect:/bookings/final", result);
    }

    @Test
    public void testCreateBooking_RoomPartiallyUnavailable() {
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000L); // 1 day after today
        Date checkOutDate = new Date(System.currentTimeMillis() + 5 * 86400000L); // 5 days after today

        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();

        // Existing booking ends during the new booking period
        BookingFinal existingBooking = new BookingFinal(new Date(System.currentTimeMillis() + 2 * 86400000L), new Date(System.currentTimeMillis() + 3 * 86400000L));
        existingBooking.setRoom(mockRoom);

        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.singletonList(existingBooking));

        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // No booking should be created
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));
        verify(model).addAttribute(eq("errorMessage"), eq("Room is not available for the selected dates."));
        assertEquals("booking-final-form-create", result);
    }

    /* ---------------------------------------------------------------------------------------------------------------------------------- */


    @Test
    public void testCreateBooking_PastCheckInDate() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() - 86400000); // 1 day before today
        Date checkOutDate = new Date(today.getTime() + 86400000); // 1 day after today

        // Mock the traveler and room data
        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        // Call the controller method with a past check-in date
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Check-in and check-out dates cannot be in the past."));
    }

    @Test
    public void testCreateBooking_PastCheckOutDate() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() + 86400000); // 1 day after today
        Date checkOutDate = new Date(today.getTime() - 86400000); // 1 day before today

        // Mock the traveler and room data
        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        // Call the controller method with a past check-out date
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Check-in and check-out dates cannot be in the past."));
    }

//    @Test
//    public void testCreateBooking_NullDates() {
//        // Mocked inputs
//        Integer travelerId = 1;
//        Integer roomId = 1;
//        Date checkInDate = null;
//        Date checkOutDate = null;
//
//        // Mock the traveler and room data
//        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
//        when(roomService.getRoomById(roomId)).thenReturn(new Room());
//
//        // Call the controller method with null dates
//        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);
//
//        // Verify no booking creation
//        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));
//
//        // Assert that the correct view is returned
//        assertEquals("booking-final-form-create", result);
//
//        // Verify error message is set in the model
//        verify(model).addAttribute(eq("errorMessage"), eq("Invalid dates. Check-in date should be before check-out date."));
//    }

    @Test
    public void testCreateBooking_CheckInAfterCheckOut() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 172800000); // 2 days from now
        Date checkOutDate = new Date(System.currentTimeMillis() + 86400000); // 1 day from now

        // Mock the traveler and room data
        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        // Call the controller method with check-in after check-out
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Invalid dates. Check-in date should be before check-out date."));
    }

    @Test
    public void testCreateBooking_RoomUnavailable() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date checkInDate = new Date(System.currentTimeMillis() + 86400000); // 1 day from now
        Date checkOutDate = new Date(System.currentTimeMillis() + 259200000); // 3 days from now

        // Mock the traveler and room data
        Traveler mockTraveler = new Traveler();
        Room mockRoom = new Room();
        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        // Create an existing booking that overlaps with the new booking
        BookingFinal existingBooking = new BookingFinal();
        existingBooking.setCheckInDate(new Date(System.currentTimeMillis() + 172800000)); // 2 days from now
        existingBooking.setCheckOutDate(new Date(System.currentTimeMillis() + 345600000)); // 4 days from now
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Collections.singletonList(existingBooking));

        // Call the controller method with overlapping dates
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Room is not available for the selected dates."));
    }

    @Test
    public void testCreateBooking_PastDates() {
        // Mocked inputs
        Integer travelerId = 1;
        Integer roomId = 1;
        Date today = new Date();
        Date checkInDate = new Date(today.getTime() - 86400000); // 1 day before today
        Date checkOutDate = new Date(today.getTime() + 86400000); // 1 day after today

        // Mock the traveler and room data
        when(travelerService.getTraveler(travelerId)).thenReturn(new Traveler());
        when(roomService.getRoomById(roomId)).thenReturn(new Room());

        // Call the controller method with a past check-in date
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking creation
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert that the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Check-in and check-out dates cannot be in the past."));
    }

    @Test
    public void testCreateBooking_OverlappingBookings() {
        Integer travelerId = 1;
        Integer roomId = 1;

        // Create dates for the new booking
        Date checkInDate = new Date(System.currentTimeMillis() + 1 * 86400000L); // 1 day after today
        Date checkOutDate = new Date(System.currentTimeMillis() + 3 * 86400000L); // 3 days after today

        // Create two existing bookings that overlap with the new booking
        BookingFinal existingBooking1 = new BookingFinal(
                new Date(System.currentTimeMillis() + 1 * 86400000L), // overlaps: starts the same day
                new Date(System.currentTimeMillis() + 2 * 86400000L)  // overlaps: ends before new check-out
        );
        BookingFinal existingBooking2 = new BookingFinal(
                new Date(System.currentTimeMillis() + 2 * 86400000L), // overlaps: starts before new check-out
                new Date(System.currentTimeMillis() + 4 * 86400000L)  // overlaps: ends after new check-out
        );

        // Assign the same room to existing bookings
        existingBooking1.setRoom(new Room());
        existingBooking1.getRoom().setId(roomId);
        existingBooking2.setRoom(new Room());
        existingBooking2.getRoom().setId(roomId);

        Traveler mockTraveler = new Traveler();
        when(travelerService.getTraveler(travelerId)).thenReturn(mockTraveler);

        Room mockRoom = new Room();
        when(roomService.getRoomById(roomId)).thenReturn(mockRoom);

        // Provide the existing bookings
        when(bookingFinalService.getBookingsByRoom(roomId)).thenReturn(Arrays.asList(existingBooking1, existingBooking2));

        // Call the controller method with overlapping booking dates
        String result = bookingFinalController.createBooking(travelerId, roomId, checkInDate, checkOutDate, model);

        // Verify no booking is created
        verify(bookingFinalService, never()).createBooking(any(Traveler.class), any(Room.class), any(Date.class), any(Date.class));

        // Assert the correct view is returned
        assertEquals("booking-final-form-create", result);

        // Verify error message is set in the model
        verify(model).addAttribute(eq("errorMessage"), eq("Room is not available for the selected dates."));
        verify(model).addAttribute(eq("travelers"), any());
        verify(model).addAttribute(eq("hotelsWithRooms"), any());
    }


}
