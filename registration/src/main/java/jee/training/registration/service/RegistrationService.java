package jee.training.registration.service;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jee.training.registration.dao.AttendeeDao;
import jee.training.registration.interceptor.Monitor;
import jee.training.registration.model.Attendee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ManagedBean
public class RegistrationService implements Serializable {
    @Inject
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private AttendeeDao attendeeDao;

    Map<String, List<Attendee>> events = new HashMap<>();

    @PostConstruct
    public void init() {
        Attendee a1 = new Attendee("test", "test@email.de");
        Attendee a2 = new Attendee("abc", "a@b.c");
        Attendee a3 = new Attendee("xyz", "x@y.z");
        events = new HashMap<>(); // Map.of("2022-09-22", List.of(a1, a2), "2022-10-14", List.of(), "2022-11-13", List.of(a1, a2, a3));
    }

    public Map<String, List<Attendee>> getEvents() {
        return events;
    }

    @Monitor
    public void addAttendee(String eventId, Attendee attendee) {
        if (!events.containsKey(eventId)) {
            events.put(eventId, new ArrayList<>());
        }
        events.get(eventId).add(attendee);
        Attendee saved = attendeeDao.save(attendee);
        System.out.println("Aus DB geladen: " + attendeeDao.get(saved.getId()));
        simulateUpdatesAndDelete(saved.getId());
        System.out.println("Aus DB geladen: " + attendeeDao.get(saved.getId()));
        logger.log(Level.INFO, "added attendee " + attendee + " with id #" + saved.getId());
    }

    // @Transactional(Transactional.TxType.REQUIRED)
    public void simulateUpdatesAndDelete(Long id) {
        Attendee attendee = attendeeDao.get(id);
        attendee.setName("embarc");
        attendee.setEmail("fs@embarc.de");
        Attendee merged = attendeeDao.update(attendee);
        Attendee deleted = attendeeDao.delete(id);
        System.out.println("Datensatz  mit ID #" + id + " gelöscht? " + (em.find(Attendee.class, id) == null));
    }
}
