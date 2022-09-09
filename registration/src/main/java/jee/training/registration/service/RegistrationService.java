package jee.training.registration.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jee.training.registration.dao.AttendeeDao;
import jee.training.registration.dao.EventDao;
import jee.training.registration.interceptor.Monitor;
import jee.training.registration.model.Attendee;
import jee.training.registration.model.Event;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional
public class RegistrationService implements Serializable {
    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private AttendeeDao attendeeDao;

    @Inject
    private EventDao eventDao;

    public List<Event> getEvents() {
        return eventDao.findAll();
    }

    @Monitor
    public void addAttendee(String eventId, Attendee attendee) {
        Event event = em.find(Event.class, eventId);
        event.addToAttendees(attendee);
        Attendee saved = attendeeDao.save(attendee);
        logger.log(Level.INFO, "added attendee " + attendee + " with id #" + saved.getId());
    }

    public void simulateDirtyChecking(Long id) {
        Attendee attendee = attendeeDao.get(id);
        attendee.setName("Falk");
        attendee.setEmail("fs@embarc.de");
    }

    public void simulateUpdateDetachedObject(Attendee attendee) {
        attendee.setName("Dieter");
        attendee.setEmail("dieter@develop.de");
        System.out.println("bevor Merge");
        Attendee merged = attendeeDao.update(attendee);
        System.out.println("nach Merge");
    }

    public void simulateDelete(Attendee attendee) {
        Attendee deleted = attendeeDao.delete(attendee);
        System.out.println("Datensatz mit ID #" + attendee.getId() + " gelöscht? " + (em.find(Attendee.class, attendee.getId()) == null));
    }
}
