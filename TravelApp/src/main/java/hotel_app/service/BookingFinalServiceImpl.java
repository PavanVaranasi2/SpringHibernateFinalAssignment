package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Room;
import hotel_app.model.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class BookingFinalServiceImpl implements BookingFinalService {

    private final BookingFinalDAO bookingFinalDAO;

    @Autowired
    public BookingFinalServiceImpl(BookingFinalDAO bookingFinalDAO) {
        this.bookingFinalDAO = bookingFinalDAO;
    }

    public void createBooking(Traveler traveler, Room room, Date checkIn, Date checkOut) {
        BookingFinal booking = new BookingFinal();
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setTraveler(traveler);
        booking.setRoom(room);
        bookingFinalDAO.save(booking);
    }

    public List<BookingFinal> getAllBookings() {
        List<BookingFinal> bookings = bookingFinalDAO.getAllBookings();

        // Sort the bookings based on check-in date
        bookings.sort(Comparator.comparing(BookingFinal::getCheckInDate));

        return bookings;
    }

    public List<BookingFinal> getBookingsByTraveler(int travelerId) {
        return bookingFinalDAO.findByTravelerId(travelerId);
    }

    public List<BookingFinal> getBookingByRoom(int roomId) {
        return bookingFinalDAO.findByRoomId(roomId);
    }

    public void deleteBooking(int bookingId) {
        bookingFinalDAO.deleteById(bookingId);
    }

    @Override
    public List<BookingFinal> getBookingsByRoom(Integer roomId) {
        return bookingFinalDAO.findByRoomId(roomId);
    }

    @Override
    @Transactional
    public BookingFinal getBookingById(Integer bookingId) {
        return bookingFinalDAO.getBookingById(bookingId);
    }
}
