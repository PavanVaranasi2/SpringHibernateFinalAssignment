package hotel_app.service;

import hotel_app.model.BookingFinal;
import hotel_app.model.Room;
import hotel_app.model.Traveler;

import java.util.Date;
import java.util.List;

public interface BookingFinalService {

    void createBooking(Traveler traveler, Room room, Date checkIn, Date checkOut);

    List<BookingFinal> getAllBookings();

    List<BookingFinal> getBookingsByTraveler(int travelerId);

    List<BookingFinal> getBookingByRoom(int roomId);

    void deleteBooking(int bookingId);

    List<BookingFinal> getBookingsByRoom(Integer roomId);

    BookingFinal getBookingById(Integer bookingId);
}
