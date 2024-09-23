package hotel_app.dao;

import hotel_app.model.Traveler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TravelerDAOImpl implements TravelerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Traveler> getTravelers() {
        Session session = sessionFactory.getCurrentSession();
        Query<Traveler> query = session.createQuery("from Traveler", Traveler.class);
        return query.getResultList();
    }

    @Override
    public void saveTraveler(Traveler traveler) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(traveler);
    }

    @Override
    public Traveler getTraveler(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Traveler.class, id);
    }

    @Override
    public void deleteTraveler(int id) {
        Session session = sessionFactory.getCurrentSession();
        Traveler traveler = session.get(Traveler.class, id);
        if (traveler != null) {
            session.delete(traveler);
        }
    }
}
