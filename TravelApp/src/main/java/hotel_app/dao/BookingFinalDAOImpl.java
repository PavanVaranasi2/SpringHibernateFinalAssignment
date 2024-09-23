package hotel_app.dao;

import hotel_app.model.BookingFinal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookingFinalDAOImpl implements BookingFinalDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(BookingFinal booking) {
        sessionFactory.getCurrentSession().save(booking);
    }

    @Transactional
    public List<BookingFinal> getAllBookings() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from BookingFinal b join fetch b.traveler join fetch b.room", BookingFinal.class)
                .getResultList();
    }

    @Transactional
    public List<BookingFinal> findByTravelerId(int travelerId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from BookingFinal b where b.traveler.id = :id", BookingFinal.class)
                .setParameter("id", travelerId)
                .getResultList();
    }

    @Transactional
    public List<BookingFinal> findByRoomId(int roomId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from BookingFinal b where b.room.id = :id", BookingFinal.class)
                .setParameter("id", roomId)
                .getResultList();
    }

    @Transactional
    public void deleteById(int bookingId) {
        BookingFinal booking = sessionFactory
                .getCurrentSession()
                .get(BookingFinal.class, bookingId);
        if (booking != null) {
            sessionFactory.getCurrentSession().delete(booking);
        }
    }

    @Override
    public BookingFinal getBookingById(Integer bookingId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(BookingFinal.class, bookingId);
    }
}
