package jee.training.registration.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Event {
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    @JsonbTransient
    private Date date;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonbTransient
    private Set<Attendee> attendees = new HashSet<>();

    public Event() {
    }

    public Event(String id, Location location) {
        this.id = id;
        this.date = parseDate(id);
        this.location = location;
    }

    private Date parseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void addToAttendees(Attendee attendee) {
        this.attendees.add(attendee);
        attendee.setEvent(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.date = parseDate(id);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("date=" + date)
                .add("location=" + location)
                .add("attendees=" + attendees)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id.equals(event.id) && location.equals(event.location) && attendees.equals(event.attendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
