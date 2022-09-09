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

    }

    private Date parseDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
