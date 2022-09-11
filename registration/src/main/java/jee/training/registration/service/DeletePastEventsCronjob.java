package jee.training.registration.service;

import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jee.training.registration.dao.EventDao;
import jee.training.registration.model.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
public class DeletePastEventsCronjob {
    @Inject
    private EventDao eventDao;

    @Inject
    private Logger logger;

    /**
     * Prüft alle 30 Sekunden, ob Events abgelaufen sind (älter als 7 Tage) und löscht diese
     */
    @Schedule(second = "*/30", minute = "*", hour = "*", persistent = false)
    public void deleteEventData() {
        LocalDate date = LocalDate.now().minusDays(7);
        List<Event> eventsToDelete = eventDao.findByDateBefore(date);
        logger.info(String.format("Lösche %s abgelaufene Events: %s", eventsToDelete.size(), eventsToDelete.stream().map(e -> e.getId()).collect(Collectors.toList())));
        eventsToDelete.forEach(e -> {
            eventDao.delete(e.getId());
        });
    }

}
