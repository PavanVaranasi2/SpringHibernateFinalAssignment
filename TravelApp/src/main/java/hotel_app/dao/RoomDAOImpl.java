package hotel_app.dao;

import hotel_app.model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoomDAOImpl implements RoomDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoomDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Room> getAllRooms() {
        Session session = sessionFactory.getCurrentSession();
        Query<Room> query = session.createQuery("from Room", Room.class);
        return query.getResultList();
    }

    @Override
    public void saveRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(room);
    }

    @Override
    public Room getRoomById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Room.class, id);
    }

    @Override
    public void deleteRoom(int id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.byId(Room.class).load(id);
        session.delete(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> getRoomsByHotel(int hotelId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Room WHERE hotel.id = :hotelId", Room.class)
                .setParameter("hotelId", hotelId)
                .getResultList();
    }
}
