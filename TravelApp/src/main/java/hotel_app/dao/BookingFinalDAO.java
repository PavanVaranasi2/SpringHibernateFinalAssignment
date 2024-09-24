package hotel_app.dao;

import hotel_app.model.BookingFinal;

import java.util.List;

public interface BookingFinalDAO {

    void save(BookingFinal booking);

    List<BookingFinal> getAllBookings();

    List<BookingFinal> findByTravelerId(int travelerId);

    List<BookingFinal> findByRoomId(int roomId);

    void deleteById(int bookingId);

    BookingFinal getBookingById(Integer bookingId);
}
