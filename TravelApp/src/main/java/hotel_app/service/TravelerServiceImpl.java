package hotel_app.service;

import hotel_app.dao.BookingFinalDAO;
import hotel_app.dao.TravelerDAO;
import hotel_app.dto.TravelerDTO;
import hotel_app.model.BookingFinal;
import hotel_app.model.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravelerServiceImpl implements TravelerService {

    private final TravelerDAO travelerDAO;
    private final BookingFinalDAO bookingFinalDAO;

    @Autowired
    public TravelerServiceImpl(TravelerDAO travelerDAO, BookingFinalDAO bookingFinalDAO) {
        this.travelerDAO = travelerDAO;
        this.bookingFinalDAO = bookingFinalDAO;
    }

    @Override
    @Transactional
    public List<Traveler> getTravelers() {
        return travelerDAO.getTravelers();
    }

    @Override
    @Transactional
    public void saveTraveler(Traveler traveler) {
        travelerDAO.saveTraveler(traveler);
    }

    @Override
    @Transactional
    public Traveler getTraveler(int id) {
        return travelerDAO.getTraveler(id);
    }

    @Override
    @Transactional
    public void deleteTraveler(int id) {
        List<BookingFinal> bookings = bookingFinalDAO.findByTravelerId(id);

        if (!bookings.isEmpty()) {
            throw new IllegalStateException("Traveler cannot be deleted as they have active bookings.");
        }

        travelerDAO.deleteTraveler(id);
    }

    @Override
    @Transactional
    public List<TravelerDTO> getTravelerDTOs() {
        List<Traveler> travelers = travelerDAO.getTravelers();
        List<TravelerDTO> travelerDTOs = new ArrayList<>();

        for (Traveler traveler : travelers) {
            TravelerDTO dto = new TravelerDTO(traveler.getId(), traveler.getName(), traveler.getEmail(), traveler.getPhoneNumber());
            travelerDTOs.add(dto);
        }

        return travelerDTOs;
    }

}
