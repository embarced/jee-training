package jee.training.registration.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Event {
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    @JsonbDateFormat("dd.MM.yyyy")
    private Date date;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "event")
    private Set<Attendee> attendees;

    private Event() {
    }

    public Event(String id, Date date, Location location) {
        this.id = id;
        this.date = date;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void addToAttendees(Attendee attendee) {
        this.attendees.add(attendee);
        attendee.setEvent(this);
    }

    public Set<Attendee> getAttendes() {
        return attendees;
    }
}
