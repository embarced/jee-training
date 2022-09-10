package jee.training.registration.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Event event;

    public Attendee() {
    }

    public Attendee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Attendee(String name, String email, Event event) {
        this(name, email);
        this.event = event;
        //this.event.getAttendes().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendee attendee = (Attendee) o;
        return Objects.equals(name, attendee.name) && Objects.equals(email, attendee.email) && Objects.equals(event, attendee.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, event);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Attendee.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .toString();
    }

}
