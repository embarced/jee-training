package jee.training.registration.service;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jee.training.registration.model.Attendee;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionScoped
@ManagedBean
public class RegistrationSessionService implements Serializable {

    Map<String, List<Attendee>> events = new HashMap<>();

    @PostConstruct
    public void init() {
        Attendee a1 = new Attendee("test", "test@email.de");
        Attendee a2 = new Attendee("abc", "a@b.c");
        Attendee a3 = new Attendee("xyz", "x@y.z");
        events = Map.of("2022-09-22", List.of(a1, a2), "2022-10-14", List.of(), "2022-11-13", List.of(a1, a2, a3));
    }

    public Map<String, List<Attendee>> getEvents() {
        return events;
    }
}
