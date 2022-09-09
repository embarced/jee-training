package jee.training.registration.model;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity
public class Event {
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    @JsonbDateFormat("dd.MM.yyyy")
    private Date date;

    private Event() {
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
