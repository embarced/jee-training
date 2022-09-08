package jee.training.registration.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jee.training.registration.model.Attendee;

@Transactional(Transactional.TxType.MANDATORY)
public class AttendeeDao {
    @PersistenceContext
    private EntityManager em;

    public Attendee save(Attendee attendee) {
        em.persist(attendee);
        return attendee;
    }

    public Attendee get(Long id) {
        return em.find(Attendee.class, id);
    }

    public Attendee update(Attendee attendee) {
        return em.merge(attendee);
    }

    public Attendee delete(Attendee attendee) {
        Attendee toDelete = this.get(attendee.getId());
        em.remove(toDelete);
        return toDelete;
    }
}
