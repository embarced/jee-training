package jee.training.registration.config;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jee.training.registration.model.Attendee;
import jee.training.registration.model.Event;
import jee.training.registration.model.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Singleton
@Startup
public class Bootstrap {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SimpleDateFormat dateFormat;

    @PostConstruct
    public void init() {
        Location darmstadt = new Location(1L, "Darmstadt");
        Location frankfurt = new Location(2L, "Frankfurt");
        Location online = new Location(3L, "Online");

        Event e1 = new Event("2022-09-22", parseDate("2022-09-22"), darmstadt);
        Event e2 = new Event("2022-10-13", parseDate("2022-10-13"), online);
        Event e3 = new Event("2022-11-10", parseDate("2022-11-10"), online);

        if (em.createQuery("select count(e) from Event e", Long.class).getSingleResult() == 0) {
            List.of(e1, e2, e3).forEach(e -> em.persist(e));
        }

        if (em.createQuery("select count(l) from Location l", Long.class).getSingleResult() == 0) {
            List.of(darmstadt, frankfurt, online).forEach(l -> em.persist(l));
        }

    }

    private Date parseDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
