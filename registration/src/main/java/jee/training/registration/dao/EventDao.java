package jee.training.registration.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jee.training.registration.model.Event;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
public class EventDao {

    @PersistenceContext
    private EntityManager em;

    public List<Event> findAll() {
        return em.createQuery("select distinct e from Event e left join fetch e.attendees", Event.class).getResultList();
    }

    public List<Event> findByDate(Date date) {
        return List.of();
    }

    public Event get(String id) {
        return em.find(Event.class, id);
    }

    public Event save(Event event) {
        em.persist(event);
        return event;
    }

    public Event delete(String id) {
        Event toDelete = em.find(Event.class, id);
        em.remove(toDelete);
        return toDelete;
    }

    public List<Event> findByDateBefore(LocalDate date) {
        em.createQuery("select e from Event where date < :date", Event.class).setParameter("date", date).getResultList();
        return List.of();
    }
}
