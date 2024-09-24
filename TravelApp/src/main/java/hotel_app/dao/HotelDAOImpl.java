package hotel_app.dao;

import hotel_app.model.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelDAOImpl implements HotelDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public HotelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Hotel> getAllHotels() {
        Session session = sessionFactory.getCurrentSession();
        Query<Hotel> query = session.createQuery("from Hotel", Hotel.class);
        return query.getResultList();
    }

    @Override
    public void saveHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(hotel);
    }

    @Override
    public Hotel getHotelById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Hotel.class, id);
    }

    @Override
    public void deleteHotel(int id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.byId(Hotel.class).load(id);
        session.delete(hotel);
    }

    @Override
    public List<Hotel> getHotelsWithRooms() {
        Session session = sessionFactory.getCurrentSession();

        Query<Hotel> query = session.createQuery("SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.rooms", Hotel.class);

        return query.getResultList();
    }
}
