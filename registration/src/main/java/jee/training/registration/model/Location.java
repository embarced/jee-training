package jee.training.registration.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Location {

    @Id
    private Long id;

    private String name;

    private Location() {
    }

    public Location(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
