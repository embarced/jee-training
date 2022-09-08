package jee.training.registration.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jee.training.registration.model.Attendee;

public class AttendeeDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Attendee save(Attendee attendee) {
        em.persist(attendee);
        return attendee;
    }

    public Attendee get(Long id) {
        return em.find(Attendee.class, id);
    }
}
